package pl.ogarnizer.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ogarnizer.business.dao.ClosedAwayWorkDAO;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.ClosedAwayWork;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ClosedAwayWorkService {
    private final ClosedAwayWorkDAO closedAwayWorkDAO;

    @Transactional
    public List<ClosedAwayWork> findClosedAwayWorks(){
        return closedAwayWorkDAO.findAll();
    }
    @Transactional
    public Page<ClosedAwayWork> findClosedAwayWorks(Pageable pageRequest, String keyword){
        return closedAwayWorkDAO.findAll(pageRequest, keyword);
    }

    @Transactional
    public void deleteClosedAwayWork(Integer closedAwayWorkId){
        closedAwayWorkDAO.deleteClosedAwayWork(closedAwayWorkId);
    }
}
