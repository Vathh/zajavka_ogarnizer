package pl.ogarnizer.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ogarnizer.business.dao.PriorityDAO;
import pl.ogarnizer.domain.Priority;
import pl.ogarnizer.domain.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PriorityService {

    private final PriorityDAO priorityDAO;

    @Transactional
    public List<Priority> findPriorities(){
        return priorityDAO.findAll();
    }

    @Transactional
    public Priority findPriority(String priorityName){
        Optional<Priority> priority = priorityDAO.findByName(priorityName);

        if(priority.isEmpty()){
            throw new NotFoundException("Could not find user by user name: [%s]".formatted(priorityName));
        }
        return priority.get();
    }
}
