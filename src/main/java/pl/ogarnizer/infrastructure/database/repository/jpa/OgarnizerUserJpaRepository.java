package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ogarnizer.infrastructure.security.UserEntity;

import java.util.Optional;

public interface OgarnizerUserJpaRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUserName(String name);
}
