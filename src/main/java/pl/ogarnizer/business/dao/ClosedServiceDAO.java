package pl.ogarnizer.business.dao;

import pl.ogarnizer.domain.Client;
import pl.ogarnizer.domain.ClosedService;
import pl.ogarnizer.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface ClosedServiceDAO {
    List<ClosedService> findAll();
    List<ClosedService> findByCreatingDate(LocalDate date);
    void addClosedService(ClosedService closedService);
    void deleteClosedService(Integer closedServiceId);
}
