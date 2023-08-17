package pl.ogarnizer.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.business.dao.PriorityDAO;
import pl.ogarnizer.domain.Priority;
import pl.ogarnizer.infrastructure.database.repository.jpa.PriorityJpaRepository;
import pl.ogarnizer.infrastructure.database.repository.mapper.PriorityEntityMapper;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PriorityRepository implements PriorityDAO {

    private final PriorityJpaRepository priorityJpaRepository;

    private final PriorityEntityMapper priorityEntityMapper;

    @Override
    public List<Priority> findAll() {
        return priorityJpaRepository.findAll().stream()
                .map(priorityEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Optional<Priority> findByName(String name) {
        return priorityJpaRepository.findByName(name).map(priorityEntityMapper::mapFromEntity);
    }
}
