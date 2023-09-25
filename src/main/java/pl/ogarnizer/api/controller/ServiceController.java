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
import pl.ogarnizer.api.dto.mapper.ServiceMapper;
import pl.ogarnizer.api.dto.mapper.TaskMapper;
import pl.ogarnizer.api.ogarnizerAPI.dao.OgarnizerAPIDAO;
import pl.ogarnizer.business.*;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Service;
import pl.ogarnizer.domain.Task;

import java.util.*;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor
public class ServiceController {

    static final String SERVICE = "/service";
    static final String ADD_SERVICE = "/service/add";
    static final String CLOSE_SERVICE = "/service/close/{serviceId}/{success}";
    static final String SERVICE_DETAILS = "/service/show/{serviceId}";
    static final String UPDATE_SERVICE = "/service/update/{serviceId}";
    static final String LOAD_RANDOM_SERVICES = "/service/load";

    private final ServiceService serviceService;
    private final PriorityService priorityService;
    private final ClientService clientService;
    private final StageService stageService;
    private final ClosingTaskService closingTaskService;
    private final OgarnizerAPIDAO ogarnizerAPIDAO;
    private final ServiceMapper serviceMapper;
    private final TaskMapper taskMapper;

    @GetMapping(value = SERVICE)
    public ModelAndView servicePage(
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

        return new ModelAndView("service", data);
    }

    private Map<String, ?> prepareNecessaryData(Pageable pageRequest, String keyword){
        Page<Service> servicesPage = serviceService.findServices(pageRequest, keyword);

        var services =  servicesPage.stream()
                .map(serviceMapper::map)
                .toList();

        var taskDTO = new TaskDTO();
        var clients = clientService.findClients();
        var priorities = priorityService.findPriorities();
        var sortByFields = List.of("priority", "createdDate", "stage");
        var sortDirections = List.of("DESCENDING", "ASCENDING");

        Map<String, Object> data = new HashMap<>(Map.of(
                "serviceDTOs", services,
                "taskDTO", taskDTO,
                "clients", clients,
                "priorities", priorities,
                "sortByFields", sortByFields,
                "sortDirections", sortDirections
        ));
        int totalPages = servicesPage.getTotalPages();
        data.put("totalPages", totalPages);
        int currentPageNumber = servicesPage.getNumber();
        data.put("currentPageNumber", currentPageNumber);

        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .toList();
            data.put("pageNumbers", pageNumbers);
        }

        return data;
    }

    @PostMapping(value = ADD_SERVICE)
    public String addService(
            @Valid @ModelAttribute("taskDTO") TaskDTO taskDTO,
            BindingResult bindingResult
    ) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        taskDTO.setCreatedByUserName(user.getUsername());

        Task task = taskMapper.map(taskDTO);

        serviceService.addService(task);

        return "redirect:/service";
    }

    @GetMapping(value = LOAD_RANDOM_SERVICES)
    public String loadRandomServices() {

        List<Service> services = ogarnizerAPIDAO.getServices();
        services.forEach(service -> clientService.addClient(service.getClient()));
        List<Service> servicesToAdd = services.stream().map(service -> service.withClient(clientService.findByName(service.getClient().getName()))).toList();

        serviceService.addServices(servicesToAdd);

        return "redirect:/service";
    }

    @DeleteMapping(value = CLOSE_SERVICE)
    public String deleteService(
            @PathVariable("serviceId") Integer serviceId,
            @PathVariable("success") Boolean success
    ){
        Service serviceById = serviceService.findServiceById(serviceId);
        User securityUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String closingUserName = securityUser.getUsername();

        closingTaskService.closeService(serviceById, closingUserName, success);

        serviceService.deleteService(serviceId);
        return "redirect:/service";
    }

    @GetMapping(value = SERVICE_DETAILS)
    public String serviceDetailsPage(@PathVariable Integer serviceId, Model model){
        var service = serviceService.findServiceById(serviceId);
        var task = taskMapper.map(service);
        var taskDTO = taskMapper.map(task);
        var priorities = priorityService.findPriorities();
        var stages = stageService.findStages();
        UpdateTaskDTO updateTaskDTO = new UpdateTaskDTO();

        String taskType = "SERVICE";

        model.addAttribute("taskDTO", taskDTO);
        model.addAttribute("updateTaskDTO", updateTaskDTO);
        model.addAttribute("priorities", priorities);
        model.addAttribute("stages", stages);
        model.addAttribute("taskType", taskType);

        return "task_details";
    }

    @PutMapping(value = UPDATE_SERVICE)
    public String updateService(
            @PathVariable Integer serviceId,
            @ModelAttribute("updateTaskDTO") UpdateTaskDTO updateTaskDTO,
            BindingResult bindingResult
    ){
        var service = serviceService.findServiceById(serviceId);

        Service serviceToSave = service
                .withUpdateInfo(updateTaskDTO.getUpdateInfo())
                .withPriority(priorityService.findPriority(updateTaskDTO.getPriorityName()))
                .withStage(stageService.findStage(updateTaskDTO.getStageName()));

        serviceService.saveService(serviceToSave);

        return "redirect:/service";
    }
}
