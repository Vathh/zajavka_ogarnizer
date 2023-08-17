package pl.ogarnizer.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.UpdateTaskDTO;
import pl.ogarnizer.api.dto.mapper.ServiceMapper;
import pl.ogarnizer.api.dto.mapper.TaskMapper;
import pl.ogarnizer.api.ogarnizerAPI.dao.OgarnizerAPIDAO;
import pl.ogarnizer.business.*;
import pl.ogarnizer.domain.Service;
import pl.ogarnizer.domain.Task;

import java.util.List;
import java.util.Map;

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
    public ModelAndView servicePage(){
        Map<String, ?> data = prepareNecessaryData();
        return new ModelAndView("service", data);
    }

    private Map<String, ?> prepareNecessaryData(){
        var services =  serviceService.findServices().stream()
                .map(serviceMapper::map)
                .toList();

        var taskDTO = new TaskDTO();
        var clients = clientService.findClients();
        var priorities = priorityService.findPriorities();

        return Map.of(
                "serviceDTOs", services,
                "taskDTO", taskDTO,
                "clients", clients,
                "priorities", priorities
        );
    }

    @PostMapping(value = ADD_SERVICE)
    public String addService(
            @Valid @ModelAttribute("taskDTO") TaskDTO taskDTO,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()){
            return "service";
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
