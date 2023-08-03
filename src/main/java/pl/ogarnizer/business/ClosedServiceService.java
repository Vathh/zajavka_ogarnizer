package pl.ogarnizer.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ogarnizer.business.dao.ClosedServiceDAO;
import pl.ogarnizer.domain.ClosedService;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ClosedServiceService {

    private final ClosedServiceDAO closedServiceDAO;

    @Transactional
    public List<ClosedService> findClosedServices(){
        List<ClosedService> closedServices = closedServiceDAO.findAll();
        log.info("Closed services: [{}]", closedServices.size());
        return closedServices;
    }

    @Transactional
    public void deleteClosedService(Integer closedServiceId){
        closedServiceDAO.deleteClosedService(closedServiceId);
    }
}
