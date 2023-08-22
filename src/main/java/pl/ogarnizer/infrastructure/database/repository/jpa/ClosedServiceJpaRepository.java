package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.infrastructure.database.entity.ClosedServiceEntity;

import java.util.Set;

@Repository
public interface ClosedServiceJpaRepository extends JpaRepository<ClosedServiceEntity, Integer> {

    @Query("""
            SELECT cs FROM ClosedServiceEntity cs
            WHERE date_trunc('day', cs.createdDate) = :date            
            """)
    Set<ClosedServiceEntity> findByCreatedDate(String date);
}
