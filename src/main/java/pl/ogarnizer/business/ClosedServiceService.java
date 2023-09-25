package pl.ogarnizer.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ogarnizer.business.dao.ClosedServiceDAO;
import pl.ogarnizer.domain.ClosedAwayWork;
import pl.ogarnizer.domain.ClosedService;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ClosedServiceService {

    private final ClosedServiceDAO closedServiceDAO;

    @Transactional
    public List<ClosedService> findClosedServices(){
        return closedServiceDAO.findAll();
    }

    @Transactional
    public Page<ClosedService> findClosedServices(Pageable pageRequest, String keyword){
        return closedServiceDAO.findAll(pageRequest, keyword);
    }

    @Transactional
    public void deleteClosedService(Integer closedServiceId){
        closedServiceDAO.deleteClosedService(closedServiceId);
    }
}
