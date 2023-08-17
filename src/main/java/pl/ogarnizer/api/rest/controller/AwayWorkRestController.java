package pl.ogarnizer.api.rest.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import pl.ogarnizer.api.dto.AwayWorkDTO;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.UpdateTaskDTO;
import pl.ogarnizer.api.dto.mapper.AwayWorkMapper;
import pl.ogarnizer.api.dto.mapper.TaskMapper;
import pl.ogarnizer.api.rest.dto.AwayWorksDTO;
import pl.ogarnizer.business.AwayWorkService;
import pl.ogarnizer.business.ClosingTaskService;
import pl.ogarnizer.business.PriorityService;
import pl.ogarnizer.business.StageService;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Task;

@RestController
@AllArgsConstructor
@RequestMapping(AwayWorkRestController.API_AWAY_WORK)
public class AwayWorkRestController {

    public static final String API_AWAY_WORK = "/api/away_work";
    public static final String API_AWAY_WORK_ID = "/{awayWorkId}";
    public static final String API_DELETE_AWAY_WORK = "/{awayWorkId}/{success}/{closingUserName}";

    private final AwayWorkService awayWorkService;
    private final PriorityService priorityService;
    private final StageService stageService;
    private final ClosingTaskService closingTaskService;
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
}
