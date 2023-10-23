package pl.ogarnizer.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ogarnizer.business.dao.ServiceDAO;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Statistics;
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
    public Page<pl.ogarnizer.domain.Service> findServices(Pageable pageRequest, String keyword){
        return serviceDAO.findAll(pageRequest, keyword);
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

    @Transactional
    public Statistics getServicesStatistics(){
        long total = serviceDAO.countAll();
        long lowPriorities = serviceDAO.countByPriorityName("low");
        long mediumPriorities = serviceDAO.countByPriorityName("medium");
        long highPriorities = serviceDAO.countByPriorityName("high");
        long justAdded = serviceDAO.countByStageName("just_added");
        long inProgress = serviceDAO.countByStageName("in_progress");
        long waitingForParts = serviceDAO.countByStageName("waiting_for_parts");
        long toInvoice = serviceDAO.countByStageName("to_invoice");

        return Statistics.builder()
                .name("Services")
                .total(total)
                .lowPrioritiesPercentage((short) (lowPriorities * 100 / total))
                .mediumPrioritiesPercentage((short) (mediumPriorities * 100 / total))
                .highPrioritiesPercentage((short) (highPriorities * 100 / total))
                .justAdded(justAdded)
                .inProgress(inProgress)
                .waitingForParts(waitingForParts)
                .toInvoice(toInvoice)
                .build();
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
