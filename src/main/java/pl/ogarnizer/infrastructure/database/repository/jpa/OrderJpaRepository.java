package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.infrastructure.database.entity.AwayWorkEntity;
import pl.ogarnizer.infrastructure.database.entity.OrderEntity;

import java.util.Set;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Integer> {
    @Query("""
            SELECT od FROM OrderEntity od
            WHERE od.creatingUser.userName LIKE %:keyword%
            OR (CAST (od.createdDate as string)) LIKE %:keyword%
            OR od.client.name LIKE %:keyword%
            OR od.description LIKE %:keyword%
            OR od.device LIKE %:keyword%
            OR od.additionalInfo LIKE %:keyword%
            OR od.updateInfo LIKE %:keyword%
            """)
    Page<OrderEntity> findAllByKeywordAndSort(final @Param("keyword") String keyword,
                                                 Pageable pageable);
}
