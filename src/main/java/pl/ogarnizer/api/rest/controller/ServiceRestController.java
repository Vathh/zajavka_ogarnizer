package pl.ogarnizer.api.rest.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ogarnizer.api.dto.ServiceDTO;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.UpdateTaskDTO;
import pl.ogarnizer.api.dto.mapper.ServiceMapper;
import pl.ogarnizer.api.dto.mapper.TaskMapper;
import pl.ogarnizer.api.ogarnizerAPI.dao.OgarnizerAPIDAO;
import pl.ogarnizer.api.rest.dto.AwayWorksDTO;
import pl.ogarnizer.api.rest.dto.ServicesDTO;
import pl.ogarnizer.business.*;
import pl.ogarnizer.domain.Service;
import pl.ogarnizer.domain.Task;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(ServiceRestController.API_SERVICE)
public class ServiceRestController {

    public static final String API_SERVICE = "/api/service";
    public static final String API_SERVICE_PAGES = "/pages";
    public static final String API_SERVICE_ID = "/{serviceId}";
    public static final String API_DELETE_SERVICE = "/{serviceId}/{success}/{closingUserName}";
    public static final String LOAD_RANDOM_SERVICES = "/load";

    private final ServiceService serviceService;
    private final ClientService clientService;
    private final PriorityService priorityService;
    private final StageService stageService;
    private final ClosingTaskService closingTaskService;
    private final OgarnizerAPIDAO ogarnizerAPIDAO;
    private final ServiceMapper serviceMapper;
    private final TaskMapper taskMapper;

    @GetMapping
    public ServicesDTO getServices() {
        return ServicesDTO.builder()
                .services(
                        serviceService.findServices().stream()
                                .map(serviceMapper::map).toList())
                .build();
    }

    @GetMapping(API_SERVICE_PAGES)
    public ServicesDTO getServicesPaginated(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("keyword") Optional<String> keyword,
            @RequestParam("sortDir") Optional<String> sortDir,
            @RequestParam("sortBy") Optional<String> sortBy
    ){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(4);

        Sort.Direction sortDirection = (sortDir.isEmpty() || sortDir.get().length() == 0 || Objects.equals(sortDir.get(), "DESCENDING"))
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        Sort sort = Sort.by(sortDirection, (sortBy.isEmpty() || sortBy.get().length() == 0) ? "priority" : sortBy.get());

        Pageable pageRequest = PageRequest.of(currentPage - 1, pageSize, sort);

        return ServicesDTO.builder()
                .services(
                        serviceService.findServices(pageRequest, keyword.isEmpty() ? "" : keyword.get()).stream()
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

    @GetMapping(value = LOAD_RANDOM_SERVICES)
    public void loadRandomServices() {

        List<Service> services = ogarnizerAPIDAO.getServices();
        services.forEach(service -> clientService.addClient(service.getClient()));
        List<Service> servicesToAdd = services.stream().map(
                        service -> service.withClient(
                                clientService.findByName(
                                        service.getClient().getName())))
                .toList();
        serviceService.addServices(servicesToAdd);
    }
}
