package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.domain.User;
import pl.ogarnizer.infrastructure.database.entity.ClosedOrderEntity;

import java.util.Set;

@Repository
public interface ClosedOrderJpaRepository extends JpaRepository<ClosedOrderEntity, Integer> {

    @Query("""
            SELECT co FROM ClosedOrderEntity co
            WHERE date_trunc('day', co.createdDate) = :date            
            """)
    Set<ClosedOrderEntity> findByCreatedDate(String date);
}
