package pl.ogarnizer.business.dao;

import pl.ogarnizer.domain.Client;
import pl.ogarnizer.domain.ClosedOrder;
import pl.ogarnizer.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface ClosedOrderDAO {
    List<ClosedOrder> findAll();
    List<ClosedOrder> findByCreatingDate(LocalDate date);
    void addClosedOrder(ClosedOrder closedOrder);
    void deleteClosedOrder(Integer closedOrderId);
}
