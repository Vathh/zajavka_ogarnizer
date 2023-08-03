package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ogarnizer.infrastructure.database.entity.PriorityEntity;
import pl.ogarnizer.infrastructure.security.UserEntity;

import java.util.Optional;

public interface PriorityJpaRepository extends JpaRepository<PriorityEntity, Integer> {

    Optional<PriorityEntity> findByName(String name);
}
