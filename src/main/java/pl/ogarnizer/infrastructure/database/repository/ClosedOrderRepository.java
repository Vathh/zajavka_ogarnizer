package pl.ogarnizer.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.business.dao.ClosedOrderDAO;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.domain.ClosedOrder;
import pl.ogarnizer.domain.User;
import pl.ogarnizer.infrastructure.database.entity.ClosedOrderEntity;
import pl.ogarnizer.infrastructure.database.repository.jpa.ClosedOrderJpaRepository;
import pl.ogarnizer.infrastructure.database.repository.mapper.ClosedOrderEntityMapper;

import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class ClosedOrderRepository implements ClosedOrderDAO {

    private final ClosedOrderJpaRepository closedOrderJpaRepository;

    private final ClosedOrderEntityMapper closedOrderEntityMapper;

    @Override
    public List<ClosedOrder> findAll() {
        return closedOrderJpaRepository.findAll().stream()
                .map(closedOrderEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<ClosedOrder> findByCreatingUser(User user) {
        return closedOrderJpaRepository.findByCreatingUser(user).stream()
                .map(closedOrderEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<ClosedOrder> findByCreatingDate(LocalDate date) {
        return closedOrderJpaRepository.findByCreatedDate(date.toString()).stream()
                .map(closedOrderEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<ClosedOrder> findByClient(Client client) {
        return closedOrderJpaRepository.findByClient(client).stream()
                .map(closedOrderEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<ClosedOrder> findByClosingUser(User user) {
        return closedOrderJpaRepository.findByClosingUser(user).stream()
                .map(closedOrderEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<ClosedOrder> findByClosingDate(LocalDate date) {
        return closedOrderJpaRepository.findByClosedDate(date.toString()).stream()
                .map(closedOrderEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public void addClosedOrder(ClosedOrder closedOrder) {
        ClosedOrderEntity closedOrderToSave = closedOrderEntityMapper.mapToEntity(closedOrder);
        closedOrderJpaRepository.saveAndFlush(closedOrderToSave);
    }

    @Override
    public void deleteClosedOrder(Integer closedOrderId) {
        closedOrderJpaRepository.deleteById(closedOrderId);
    }
}
