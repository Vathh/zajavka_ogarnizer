package pl.ogarnizer.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.UpdateTaskDTO;
import pl.ogarnizer.api.dto.mapper.AwayWorkMapper;
import pl.ogarnizer.api.dto.mapper.TaskMapper;
import pl.ogarnizer.api.ogarnizerAPI.dao.OgarnizerAPIDAO;
import pl.ogarnizer.business.*;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor
public class AwayWorkController {

    static final String AWAY_WORK = "/away_work";
    static final String ADD_AWAY_WORK = "/away_work/add";
    static final String CLOSE_AWAY_WORK = "/away_work/close/{awayWorkId}/{success}";
    static final String AWAY_WORK_DETAILS = "/away_work/show/{awayWorkId}";
    static final String UPDATE_AWAY_WORK = "/away_work/update/{awayWorkId}";
    static final String LOAD_RANDOM_AWAY_WORKS = "/away_work/load";

    private final AwayWorkService awayWorkService;
    private final PriorityService priorityService;
    private final ClientService clientService;
    private final StageService stageService;
    private final ClosingTaskService closingTaskService;
    private final OgarnizerAPIDAO ogarnizerAPIDAO;
    private final AwayWorkMapper awayWorkMapper;
    private final TaskMapper taskMapper;

    @GetMapping(value = AWAY_WORK)
    public ModelAndView awayWorkPage(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("keyword") Optional<String> keyword,
            @RequestParam("sortDir") Optional<String> sortDir,
            @RequestParam("sortBy") Optional<String> sortBy,
            @ModelAttribute("sortOption") SortOption sortOption,
            BindingResult bindingResult
    ) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Sort.Direction sortDirection = (sortDir.isEmpty() || sortDir.get().length() == 0 || Objects.equals(sortDir.get(), "DESCENDING"))
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        Sort sort = Sort.by(sortDirection, (sortBy.isEmpty() || sortBy.get().length() == 0) ? "priority" : sortBy.get());

        Pageable pageRequest = PageRequest.of(currentPage - 1, pageSize, sort);

        Map<String, ?> data = prepareNecessaryData(pageRequest, keyword.isEmpty() ? "" : keyword.get());

        return new ModelAndView("away_work", data);
    }

    private Map<String, ?> prepareNecessaryData(Pageable pageRequest, String keyword){
        Page<AwayWork> awayWorksPage = awayWorkService.findAwayWorks(pageRequest, keyword);

        var awayWorks =  awayWorksPage.stream()
                .map(awayWorkMapper::map)
                .toList();

        var taskDTO = new TaskDTO();
        var clients = clientService.findClients();
        var priorities = priorityService.findPriorities();
        var sortByFields = List.of("priority", "createdDate", "stage");
        var sortDirections = List.of("DESCENDING", "ASCENDING");

        Map<String, Object> data = new HashMap<>(Map.of(
                "awayWorkDTOs", awayWorks,
                "taskDTO", taskDTO,
                "clients", clients,
                "priorities", priorities,
                "sortByFields", sortByFields,
                "sortDirections", sortDirections
        ));
        int totalPages = awayWorksPage.getTotalPages();
        data.put("totalPages", totalPages);
        int currentPageNumber = awayWorksPage.getNumber();
        data.put("currentPageNumber", currentPageNumber);

        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .toList();
            data.put("pageNumbers", pageNumbers);
        }

        return data;
    }

    @PostMapping(value = ADD_AWAY_WORK)
    public String addAwayWork(
            @Valid @ModelAttribute("taskDTO") TaskDTO taskDTO,
            BindingResult bindingResult
    ) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        taskDTO.setCreatedByUserName(user.getUsername());

        Task task = taskMapper.map(taskDTO);

        awayWorkService.addAwayWork(task);

        return "redirect:/away_work";
    }

    @GetMapping(value = LOAD_RANDOM_AWAY_WORKS)
    public String loadRandomAwayWorks() {

        List<AwayWork> awayWorks = ogarnizerAPIDAO.getAwayWorks();
        awayWorks.forEach(awayWork -> clientService.addClient(awayWork.getClient()));
        List<AwayWork> awayWorksToAdd = awayWorks.stream().map(
                awayWork -> awayWork.withClient(
                        clientService.findByName(
                                awayWork.getClient().getName())))
                .toList();
        awayWorkService.addAwayWorks(awayWorksToAdd);
        return "redirect:/away_work";
    }

    @DeleteMapping(value = CLOSE_AWAY_WORK)
    public String deleteAwayWork(
            @PathVariable("awayWorkId") Integer awayWorkId,
            @PathVariable("success") Boolean success
    ){
        AwayWork awayWorkById = awayWorkService.findAwayWorkById(awayWorkId);
        User securityUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String closingUserName = securityUser.getUsername();

        closingTaskService.closeAwayWork(awayWorkById, closingUserName, success);

        awayWorkService.deleteAwayWork(awayWorkId);
        return "redirect:/away_work";
    }

    @GetMapping(value = AWAY_WORK_DETAILS)
    public String awayWorkDetailsPage(@PathVariable Integer awayWorkId, Model model){
        var awayWork = awayWorkService.findAwayWorkById(awayWorkId);
        var task = taskMapper.map(awayWork);
        var taskDTO = taskMapper.map(task);
        var priorities = priorityService.findPriorities();
        var stages = stageService.findStages();
        UpdateTaskDTO updateTaskDTO = new UpdateTaskDTO();

        String taskType = "AWAY_WORK";

        model.addAttribute("taskDTO", taskDTO);
        model.addAttribute("updateTaskDTO", updateTaskDTO);
        model.addAttribute("priorities", priorities);
        model.addAttribute("stages", stages);
        model.addAttribute("taskType", taskType);

        return "task_details";
    }

    @PutMapping(value = UPDATE_AWAY_WORK)
    public String updateAwayWork(
            @PathVariable Integer awayWorkId,
            @ModelAttribute("updateTaskDTO") UpdateTaskDTO updateTaskDTO,
            BindingResult bindingResult
    ){
        var awayWork = awayWorkService.findAwayWorkById(awayWorkId);

        AwayWork awayWorkToSave = awayWork
                                .withUpdateInfo(updateTaskDTO.getUpdateInfo())
                                .withPriority(priorityService.findPriority(updateTaskDTO.getPriorityName()))
                                .withStage(stageService.findStage(updateTaskDTO.getStageName()));

        awayWorkService.saveAwayWork(awayWorkToSave);

        return "redirect:/away_work";
    }
}
