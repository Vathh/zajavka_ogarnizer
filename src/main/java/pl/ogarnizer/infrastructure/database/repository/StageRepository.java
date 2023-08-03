package pl.ogarnizer.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.business.dao.StageDAO;
import pl.ogarnizer.domain.Stage;
import pl.ogarnizer.infrastructure.database.repository.jpa.StageJpaRepository;
import pl.ogarnizer.infrastructure.database.repository.mapper.StageEntityMapper;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class StageRepository implements StageDAO {

    private final StageJpaRepository stageJpaRepository;

    private final StageEntityMapper stageEntityMapper;

    @Override
    public List<Stage> findAll() {
        return stageJpaRepository.findAll().stream()
                .map(stageEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Optional<Stage> findByName(String name) {
        return stageJpaRepository.findByName(name).map(stageEntityMapper::mapFromEntity);
    }
}
