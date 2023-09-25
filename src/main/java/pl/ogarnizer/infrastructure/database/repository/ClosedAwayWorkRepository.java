package pl.ogarnizer.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.business.dao.ClosedAwayWorkDAO;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.ClosedAwayWork;
import pl.ogarnizer.infrastructure.database.entity.ClosedAwayWorkEntity;
import pl.ogarnizer.infrastructure.database.repository.jpa.ClosedAwayWorkJpaRepository;
import pl.ogarnizer.infrastructure.database.repository.mapper.ClosedAwayWorkEntityMapper;

import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class ClosedAwayWorkRepository implements ClosedAwayWorkDAO {

    private final ClosedAwayWorkJpaRepository closedAwayWorkJpaRepository;

    private final ClosedAwayWorkEntityMapper closedAwayWorkEntityMapper;


    @Override
    public List<ClosedAwayWork> findAll() {
        return closedAwayWorkJpaRepository.findAll().stream()
                .map(closedAwayWorkEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Page<ClosedAwayWork> findAll(Pageable pageRequest, String keyword) {

        if(keyword == null || keyword.isEmpty()){
            return closedAwayWorkJpaRepository
                    .findAll(pageRequest)
                    .map(closedAwayWorkEntityMapper::mapFromEntity);
        }

        return closedAwayWorkJpaRepository.findAllByKeywordAndSort(keyword,
                        pageRequest)
                .map(closedAwayWorkEntityMapper::mapFromEntity);
    }

    @Override
    public void addClosedAwayWork(ClosedAwayWork closedAwayWork) {
        ClosedAwayWorkEntity closedAwayWorkToSave = closedAwayWorkEntityMapper.mapToEntity(closedAwayWork);
        closedAwayWorkJpaRepository.saveAndFlush(closedAwayWorkToSave);
    }

    @Override
    public void deleteClosedAwayWork(Integer closedAwayWorkId) {
        closedAwayWorkJpaRepository.deleteById(closedAwayWorkId);
    }
}
