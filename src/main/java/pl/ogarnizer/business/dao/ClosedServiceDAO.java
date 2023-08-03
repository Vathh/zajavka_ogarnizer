package pl.ogarnizer.business.dao;

import pl.ogarnizer.domain.Client;
import pl.ogarnizer.domain.ClosedService;
import pl.ogarnizer.domain.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClosedServiceDAO {

    List<ClosedService> findAll();

    List<ClosedService> findByCreatingUser(User user);

    List<ClosedService> findByCreatingDate(LocalDate date);

    List<ClosedService> findByClient(Client client);

    List<ClosedService> findByClosingUser(User user);

    List<ClosedService> findByClosingDate(LocalDate date);
    void addClosedService(ClosedService closedService);
    void deleteClosedService(Integer closedServiceId);
}
