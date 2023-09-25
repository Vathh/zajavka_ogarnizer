package pl.ogarnizer.business.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderDAO {
    List<Order> findAll();
    Page<Order> findAll(Pageable pageRequest, String keyword);
    Optional<Order> findByOrderId(Integer orderId);
    void saveOrder(Order order);
    void saveOrders(List<Order> orders);
    void addOrder(Order order);
    void deleteOrder(Integer orderId);
}
