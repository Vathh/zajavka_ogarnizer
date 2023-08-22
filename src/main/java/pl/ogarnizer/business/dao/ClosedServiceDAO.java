package pl.ogarnizer.business.dao;

import pl.ogarnizer.domain.ClosedService;

import java.time.LocalDate;
import java.util.List;

public interface ClosedServiceDAO {
    List<ClosedService> findAll();
    List<ClosedService> findByCreatingDate(LocalDate date);
    void addClosedService(ClosedService closedService);
    void deleteClosedService(Integer closedServiceId);
}
