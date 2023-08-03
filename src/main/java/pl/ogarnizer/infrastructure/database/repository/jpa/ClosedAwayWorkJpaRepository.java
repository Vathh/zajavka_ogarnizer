package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.domain.User;
import pl.ogarnizer.infrastructure.database.entity.ClosedAwayWorkEntity;

import java.util.Set;

@Repository
public interface ClosedAwayWorkJpaRepository extends JpaRepository<ClosedAwayWorkEntity, Integer> {

    Set<ClosedAwayWorkEntity> findByCreatingUser(User user);

    @Query("""
            SELECT caw FROM ClosedAwayWorkEntity caw
            WHERE date_trunc('day', caw.createdDate) = :date           
            """)
    Set<ClosedAwayWorkEntity> findByCreatedDate(String date);

    Set<ClosedAwayWorkEntity> findByClient(Client client);

    Set<ClosedAwayWorkEntity> findByClosingUser(User user);

    @Query("""
            SELECT caw FROM ClosedAwayWorkEntity caw
            WHERE date_trunc('day', caw.closedDate) = :date           
            """)
    Set<ClosedAwayWorkEntity> findByClosedDate(String date);
}
