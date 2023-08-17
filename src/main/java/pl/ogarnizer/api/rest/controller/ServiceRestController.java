package pl.ogarnizer.api.rest.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ogarnizer.api.dto.ServiceDTO;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.UpdateTaskDTO;
import pl.ogarnizer.api.dto.mapper.ServiceMapper;
import pl.ogarnizer.api.dto.mapper.TaskMapper;
import pl.ogarnizer.api.rest.dto.ServicesDTO;
import pl.ogarnizer.business.ClosingTaskService;
import pl.ogarnizer.business.PriorityService;
import pl.ogarnizer.business.ServiceService;
import pl.ogarnizer.business.StageService;
import pl.ogarnizer.domain.Service;
import pl.ogarnizer.domain.Task;

@RestController
@AllArgsConstructor
@RequestMapping(ServiceRestController.API_SERVICE)
public class ServiceRestController {

    public static final String API_SERVICE = "/api/service";
    public static final String API_SERVICE_ID = "/{serviceId}";
    public static final String API_DELETE_SERVICE = "/{serviceId}/{success}/{closingUserName}";

    private final ServiceService serviceService;
    private final PriorityService priorityService;
    private final StageService stageService;
    private final ClosingTaskService closingTaskService;
    private final ServiceMapper serviceMapper;
    private final TaskMapper taskMapper;

    @GetMapping
    public ServicesDTO getServices(){
        return ServicesDTO.builder()
                .services(serviceService.findServices().stream()
                        .map(serviceMapper::map).toList())
                .build();
    }

    @GetMapping(API_SERVICE_ID)
    public ServiceDTO serviceDetails(
            @PathVariable Integer serviceId
    ){
        return serviceMapper.map(serviceService.findServiceById(serviceId));
    }

    @PostMapping
    public ServicesDTO addService(
            @Valid @RequestBody TaskDTO taskDTO
    ){
        Task task = taskMapper.map(taskDTO);
        serviceService.addService(task);
        return ServicesDTO.builder()
                .services(serviceService.findServices().stream()
                        .map(serviceMapper::map).toList())
                .build();
    }

    @PutMapping(API_SERVICE_ID)
    public ResponseEntity<?> updateService(
            @PathVariable Integer serviceId,
            @Valid @RequestBody UpdateTaskDTO updateTaskDTO
    ){
        Service service = serviceService.findServiceById(serviceId);

        Service serviceToSave = service
                .withUpdateInfo(updateTaskDTO.getUpdateInfo())
                .withPriority(priorityService.findPriority(updateTaskDTO.getPriorityName()))
                .withStage(stageService.findStage(updateTaskDTO.getStageName()));

        serviceService.saveService(serviceToSave);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(API_DELETE_SERVICE)
    public ResponseEntity<?> deleteService(
            @PathVariable Integer serviceId,
            @PathVariable Boolean success,
            @PathVariable String closingUserName
    ){
        try{
            Service serviceById = serviceService.findServiceById(serviceId);
            closingTaskService.closeService(serviceById, closingUserName, success);
            serviceService.deleteService(serviceId);
            return ResponseEntity.ok().build();
        } catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
