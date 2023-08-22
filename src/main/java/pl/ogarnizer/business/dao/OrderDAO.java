package pl.ogarnizer.business.dao;

import pl.ogarnizer.domain.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderDAO {
    List<Order> findAll();
    Optional<Order> findByOrderId(Integer orderId);
    List<Order> findByCreatingDate(LocalDate date);
    void saveOrder(Order order);
    void saveOrders(List<Order> orders);
    void addOrder(Order order);
    void deleteOrder(Integer orderId);
}
