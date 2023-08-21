package pl.ogarnizer.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ogarnizer.business.dao.ServiceDAO;
import pl.ogarnizer.domain.Task;
import pl.ogarnizer.domain.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ServiceService {
    private final ServiceDAO serviceDAO;
    private final UserService userService;
    private final PriorityService priorityService;
    private final ClientService clientService;
    private final StageService stageService;

    @Transactional
    public List<pl.ogarnizer.domain.Service> findServices(){
        return serviceDAO.findAll();
    }

    @Transactional
    public pl.ogarnizer.domain.Service findServiceById(Integer serviceId){
        Optional<pl.ogarnizer.domain.Service> serviceById = serviceDAO.findByServiceId(serviceId);
        if(serviceById.isEmpty()){
            throw new NotFoundException("Could not find Service by id: [%s]".formatted(serviceId));
        }
        return serviceById.get();
    }

    @Transactional
    public void addService(Task task){
        pl.ogarnizer.domain.Service service = prepareService(task);
        serviceDAO.addService(service);
    }

    @Transactional
    public void addServices(List<pl.ogarnizer.domain.Service> services){
        serviceDAO.saveServices(services);
    }

    @Transactional
    public void saveService(pl.ogarnizer.domain.Service service){
        serviceDAO.saveService(service);
    }

    @Transactional
    public void deleteService(Integer serviceId){
        serviceDAO.deleteService(serviceId);
    }

    private pl.ogarnizer.domain.Service prepareService(Task task){
        return pl.ogarnizer.domain.Service.builder()
                .creatingUser(userService.findUserByName(task.getCreatedByUserName()))
                .createdDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .priority(priorityService.findPriority(task.getPriorityName()))
                .client(clientService.findByName(task.getClientName()))
                .description(task.getDescription())
                .device(task.getDevice())
                .additionalInfo(task.getAdditionalInfo())
                .stage(stageService.findStages().stream().filter(stage -> stage.getName().equals("just_added")).findFirst().get())
                .build();
    }
}
