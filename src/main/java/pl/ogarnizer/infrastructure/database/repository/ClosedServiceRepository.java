package pl.ogarnizer.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.business.dao.ClosedServiceDAO;
import pl.ogarnizer.domain.ClosedAwayWork;
import pl.ogarnizer.domain.ClosedService;
import pl.ogarnizer.infrastructure.database.entity.ClosedServiceEntity;
import pl.ogarnizer.infrastructure.database.repository.jpa.ClosedServiceJpaRepository;
import pl.ogarnizer.infrastructure.database.repository.mapper.ClosedServiceEntityMapper;

import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class ClosedServiceRepository implements ClosedServiceDAO {

    private final ClosedServiceJpaRepository closedServiceJpaRepository;

    private final ClosedServiceEntityMapper closedServiceEntityMapper;

    @Override
    public List<ClosedService> findAll() {
        return closedServiceJpaRepository.findAll().stream()
                .map(closedServiceEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Page<ClosedService> findAll(Pageable pageRequest, String keyword) {

        if(keyword == null || keyword.isEmpty()){
            return closedServiceJpaRepository
                    .findAll(pageRequest)
                    .map(closedServiceEntityMapper::mapFromEntity);
        }

        return closedServiceJpaRepository.findAllByKeywordAndSort(keyword,
                        pageRequest)
                .map(closedServiceEntityMapper::mapFromEntity);
    }

    @Override
    public void addClosedService(ClosedService closedService) {
        ClosedServiceEntity closedServiceToSave = closedServiceEntityMapper.mapToEntity(closedService);
        closedServiceJpaRepository.saveAndFlush(closedServiceToSave);
    }

    @Override
    public void deleteClosedService(Integer closedServiceId) {
        closedServiceJpaRepository.deleteById(closedServiceId);
    }
}
