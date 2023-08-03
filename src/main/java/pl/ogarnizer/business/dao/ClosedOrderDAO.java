package pl.ogarnizer.business.dao;

import pl.ogarnizer.domain.Client;
import pl.ogarnizer.domain.ClosedOrder;
import pl.ogarnizer.domain.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClosedOrderDAO {
    List<ClosedOrder> findAll();
    List<ClosedOrder> findByCreatingUser(User user);
    List<ClosedOrder> findByCreatingDate(LocalDate date);
    List<ClosedOrder> findByClient(Client client);
    List<ClosedOrder> findByClosingUser(User user);
    List<ClosedOrder> findByClosingDate(LocalDate date);
    void addClosedOrder(ClosedOrder closedOrder);
    void deleteClosedOrder(Integer closedOrderId);
}
