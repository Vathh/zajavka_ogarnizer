package pl.ogarnizer.business.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.ogarnizer.domain.ClosedAwayWork;
import pl.ogarnizer.domain.ClosedService;

import java.time.LocalDate;
import java.util.List;

public interface ClosedServiceDAO {
    List<ClosedService> findAll();
    Page<ClosedService> findAll(Pageable pageRequest, String keyword);
    void addClosedService(ClosedService closedService);
    void deleteClosedService(Integer closedServiceId);
}
