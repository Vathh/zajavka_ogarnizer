package pl.ogarnizer.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ogarnizer.business.dao.AwayWorkDAO;
import pl.ogarnizer.domain.*;
import pl.ogarnizer.domain.exception.NotFoundException;

import java.time.LocalDateTime;
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
        List<AwayWork> awayWorks = awayWorkDAO.findAll();
        log.info("Away works: [{}]", awayWorks.size());
        return awayWorks;
    }

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
    public void saveAwayWork(AwayWork awayWork){
        awayWorkDAO.saveAwayWork(awayWork);
    }

    @Transactional
    public void deleteAwayWork(Integer awayWorkId){
        awayWorkDAO.deleteAwayWork(awayWorkId);
    }

    private AwayWork prepareAwayWork(Task task){
        return AwayWork.builder()
                .creatingUser(userService.findUserByName(task.getCreatedByUserName()))
                .createdDate(LocalDateTime.now())
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
