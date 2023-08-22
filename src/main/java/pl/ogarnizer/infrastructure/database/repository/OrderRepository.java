package pl.ogarnizer.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.business.dao.OrderDAO;
import pl.ogarnizer.domain.Order;
import pl.ogarnizer.infrastructure.database.entity.OrderEntity;
import pl.ogarnizer.infrastructure.database.repository.jpa.OrderJpaRepository;
import pl.ogarnizer.infrastructure.database.repository.mapper.OrderEntityMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class OrderRepository implements OrderDAO {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Override
    public List<Order> findAll() {
        return orderJpaRepository.findAll().stream()
                .map(orderEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Optional<Order> findByOrderId(Integer orderId) {
        return orderJpaRepository.findById(orderId).map(orderEntityMapper::mapFromEntity);
    }

    @Override
    public List<Order> findByCreatingDate(LocalDate date) {
        return orderJpaRepository.findByCreatedDate(date.toString()).stream()
                .map(orderEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public void saveOrder(Order order) {
        OrderEntity toSave = orderEntityMapper.mapToEntity(order);
        orderJpaRepository.save(toSave);
    }

    @Override
    public void saveOrders(List<Order> orders) {
        List<OrderEntity> orderEntities = orders.stream().map(orderEntityMapper::mapToEntity).toList();
        orderJpaRepository.saveAllAndFlush(orderEntities);
    }

    @Override
    public void addOrder(Order order) {
        OrderEntity orderToSave = orderEntityMapper.mapToEntity(order);
        orderJpaRepository.saveAndFlush(orderToSave);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        orderJpaRepository.deleteById(orderId);
    }
}
