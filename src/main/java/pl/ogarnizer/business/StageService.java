package pl.ogarnizer.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ogarnizer.business.dao.StageDAO;
import pl.ogarnizer.domain.Stage;
import pl.ogarnizer.domain.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class StageService {

    private final StageDAO stageDAO;

    @Transactional
    public List<Stage> findStages(){
        List<Stage> priorities = stageDAO.findAll();
        log.info("Priorities: [{}]", priorities.size());
        return priorities;
    }

    @Transactional
    public Stage findStage(String stageName){
        Optional<Stage> priority = stageDAO.findByName(stageName);

        if(priority.isEmpty()){
            throw new NotFoundException("Could not find stage by stage name: [%s]".formatted(stageName));
        }
        return priority.get();
    }
}
