package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ogarnizer.infrastructure.database.entity.StageEntity;

import java.util.Optional;

public interface StageJpaRepository extends JpaRepository<StageEntity, Integer> {

    Optional<StageEntity> findByName(String name);
}
