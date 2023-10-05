package pl.ogarnizer.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ogarnizer.business.dao.AwayWorkDAO;
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
public class AwayWorkService {
    private final AwayWorkDAO awayWorkDAO;
    private final UserService userService;
    private final PriorityService priorityService;
    private final ClientService clientService;
    private final StageService stageService;

    @Transactional
    public List<AwayWork> findAwayWorks(){
        return awayWorkDAO.findAll();
    }


    @Transactional
    public Page<AwayWork> findAwayWorks(Pageable pageRequest, String keyword){
        return awayWorkDAO.findAll(pageRequest, keyword);
    }

    @Transactional
    public AwayWork findAwayWorkById(Integer awayWorkId){
        Optional<AwayWork> awayWorkById = awayWorkDAO.findByAwayWorkId(awayWorkId);
        if(awayWorkById.isEmpty()){
            throw new NotFoundException("Could not find Away Work by id: [%s]".formatted(awayWorkId));
        }
        return awayWorkById.get();
    }

    @Transactional
    public void addAwayWork(Task task){
        AwayWork awayWork = prepareAwayWork(task);
        awayWorkDAO.addAwayWork(awayWork);
    }

    @Transactional
    public void addAwayWorks(List<AwayWork> awayWorks){
        awayWorkDAO.saveAwayWorks(awayWorks);
    }

    @Transactional
    public void saveAwayWork(AwayWork awayWork){
        awayWorkDAO.saveAwayWork(awayWork);
    }

    @Transactional
    public void deleteAwayWork(Integer awayWorkId){
        awayWorkDAO.deleteAwayWork(awayWorkId);
    }

    @Transactional
    public Statistics getAwayWorksStatistics(){
        long total = awayWorkDAO.countAll();
        long lowPriorities = awayWorkDAO.countByPriorityName("low");
        long mediumPriorities = awayWorkDAO.countByPriorityName("medium");
        long highPriorities = awayWorkDAO.countByPriorityName("high");
        long justAdded = awayWorkDAO.countByStageName("just_added");
        long inProgress = awayWorkDAO.countByStageName("in_progress");
        long waitingForParts = awayWorkDAO.countByStageName("waiting_for_parts");
        long toInvoice = awayWorkDAO.countByStageName("to_invoice");

        return Statistics.builder()
                .total(total)
                .lowPriorities(lowPriorities)
                .mediumPriorities(mediumPriorities)
                .highPriorities(highPriorities)
                .justAdded(justAdded)
                .inProgress(inProgress)
                .waitingForParts(waitingForParts)
                .toInvoice(toInvoice)
                .build();
    }

    public AwayWork prepareAwayWork(Task task){
        return AwayWork.builder()
                .creatingUser(userService.findUserByName(task.getCreatedByUserName()))
                .createdDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .priority(priorityService.findPriority(task.getPriorityName()))
                .client(clientService.findByName(task.getClientName()))
                .description(task.getDescription())
                .place(task.getPlace())
                .device(task.getDevice())
                .additionalInfo(task.getAdditionalInfo())
                .stage(stageService.findStages().stream().filter(stage -> stage.getName().equals("just_added")).findFirst().get())
                .build();
    }
}
