package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.infrastructure.database.entity.ClientEntity;

import java.util.Optional;

@Repository
public interface ClientJpaRepository extends JpaRepository<ClientEntity, Integer> {

    Optional<ClientEntity> findByName(String name);

    Optional<ClientEntity> findByNip(String nip);
}
