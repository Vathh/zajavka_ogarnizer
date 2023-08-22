package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.infrastructure.database.entity.OrderEntity;

import java.util.Set;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Integer> {

    @Query("""
            SELECT od FROM OrderEntity od
            WHERE date_trunc('day', od.createdDate) = :date            
            """)
    Set<OrderEntity> findByCreatedDate(String date);

}
