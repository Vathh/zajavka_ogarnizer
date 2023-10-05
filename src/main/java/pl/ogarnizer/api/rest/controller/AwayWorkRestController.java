package pl.ogarnizer.api.rest.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ogarnizer.api.dto.AwayWorkDTO;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.UpdateTaskDTO;
import pl.ogarnizer.api.dto.mapper.AwayWorkMapper;
import pl.ogarnizer.api.dto.mapper.TaskMapper;
import pl.ogarnizer.api.ogarnizerAPI.dao.OgarnizerAPIDAO;
import pl.ogarnizer.api.rest.dto.AwayWorksDTO;
import pl.ogarnizer.business.*;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Task;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(AwayWorkRestController.API_AWAY_WORK)
public class AwayWorkRestController {

    public static final String API_AWAY_WORK = "/api/away_work";
    public static final String API_AWAY_WORK_PAGES = "/pages";
    public static final String API_AWAY_WORK_ID = "/{awayWorkId}";
    public static final String API_DELETE_AWAY_WORK = "/{awayWorkId}/{success}/{closingUserName}";
    public static final String LOAD_RANDOM_AWAY_WORKS = "/load";

    private final AwayWorkService awayWorkService;
    private final ClientService clientService;
    private final PriorityService priorityService;
    private final StageService stageService;
    private final ClosingTaskService closingTaskService;
    private final OgarnizerAPIDAO ogarnizerAPIDAO;
    private final AwayWorkMapper awayWorkMapper;
    private final TaskMapper taskMapper;

    @GetMapping
    public AwayWorksDTO getAwayWorks() {
        return AwayWorksDTO.builder()
                .awayWorks(
                awayWorkService.findAwayWorks().stream()
                        .map(awayWorkMapper::map).toList())
                .build();
    }

    @GetMapping(API_AWAY_WORK_PAGES)
    public AwayWorksDTO getAwayWorksPaginated(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("keyword") Optional<String> keyword,
            @RequestParam("sortDir") Optional<String> sortDir,
            @RequestParam("sortBy") Optional<String> sortBy
    ){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Sort.Direction sortDirection = (sortDir.isEmpty() || sortDir.get().length() == 0 || Objects.equals(sortDir.get(), "DESCENDING"))
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        Sort sort = Sort.by(sortDirection, (sortBy.isEmpty() || sortBy.get().length() == 0) ? "priority" : sortBy.get());

        Pageable pageRequest = PageRequest.of(currentPage - 1, pageSize, sort);

        return AwayWorksDTO.builder()
                .awayWorks(
            awayWorkService.findAwayWorks(pageRequest, keyword.isEmpty() ? "" : keyword.get()).stream()
                    .map(awayWorkMapper::map).toList())
                .build();
    }

    @GetMapping(API_AWAY_WORK_ID)
    public AwayWorkDTO awayWorkDetails(
            @PathVariable Integer awayWorkId
    ){
        return awayWorkMapper.map(awayWorkService.findAwayWorkById(awayWorkId));
    }

    @PostMapping
    public AwayWorksDTO addAwayWork(
            @Valid @RequestBody TaskDTO taskDTO
    ){
        Task task = taskMapper.map(taskDTO);
        awayWorkService.addAwayWork(task);
        return AwayWorksDTO.builder()
                .awayWorks(awayWorkService.findAwayWorks().stream()
                                .map(awayWorkMapper::map).toList())
                .build();
    }

    @PutMapping(API_AWAY_WORK_ID)
    public ResponseEntity<?> updateAwayWork(
            @PathVariable Integer awayWorkId,
            @Valid @RequestBody UpdateTaskDTO updateTaskDTO
            ){
        AwayWork awayWork = awayWorkService.findAwayWorkById(awayWorkId);

        AwayWork awayWorkToSave = awayWork
                .withUpdateInfo(updateTaskDTO.getUpdateInfo())
                .withPriority(priorityService.findPriority(updateTaskDTO.getPriorityName()))
                .withStage(stageService.findStage(updateTaskDTO.getStageName()));

        awayWorkService.saveAwayWork(awayWorkToSave);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(API_DELETE_AWAY_WORK)
    public ResponseEntity<?> deleteAwayWork(
            @PathVariable Integer awayWorkId,
            @PathVariable Boolean success,
            @PathVariable String closingUserName
    ){
        try{
            AwayWork awayWorkById = awayWorkService.findAwayWorkById(awayWorkId);
            closingTaskService.closeAwayWork(awayWorkById, closingUserName, success);
            awayWorkService.deleteAwayWork(awayWorkId);
            return ResponseEntity.ok().build();
        } catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = LOAD_RANDOM_AWAY_WORKS)
    public void loadRandomAwayWorks() {

        List<AwayWork> awayWorks = ogarnizerAPIDAO.getAwayWorks();
        awayWorks.forEach(awayWork -> clientService.addClient(awayWork.getClient()));
        List<AwayWork> awayWorksToAdd = awayWorks.stream().map(
                        awayWork -> awayWork.withClient(
                                clientService.findByName(
                                        awayWork.getClient().getName())))
                .toList();
        awayWorkService.addAwayWorks(awayWorksToAdd);
    }
}
