package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ogarnizer.infrastructure.security.RoleEntity;

import java.util.Optional;

public interface OgarnizerRoleJpaRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByRole(String role);
}
