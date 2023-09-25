package pl.ogarnizer.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.business.dao.ClosedOrderDAO;
import pl.ogarnizer.domain.ClosedAwayWork;
import pl.ogarnizer.domain.ClosedOrder;
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
    public Page<ClosedOrder> findAll(Pageable pageRequest, String keyword) {

        if(keyword == null || keyword.isEmpty()){
            return closedOrderJpaRepository
                    .findAll(pageRequest)
                    .map(closedOrderEntityMapper::mapFromEntity);
        }

        return closedOrderJpaRepository.findAllByKeywordAndSort(keyword,
                        pageRequest)
                .map(closedOrderEntityMapper::mapFromEntity);
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
