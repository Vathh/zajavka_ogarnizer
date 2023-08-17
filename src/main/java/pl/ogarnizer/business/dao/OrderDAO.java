package pl.ogarnizer.business.dao;

import pl.ogarnizer.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderDAO {
    List<Order> findAll();
    Optional<Order> findByOrderId(Integer orderId);
    List<Order> findByCreatingUser(User user);
    List<Order> findByCreatingDate(LocalDate date);
    List<Order> findByPriority(Priority priority);
    List<Order> findByClient(Client client);
    List<Order> findByStage(Stage stage);
    void saveOrder(Order order);
    void saveOrders(List<Order> orders);
    void addOrder(Order order);
    void deleteOrder(Integer orderId);
}
