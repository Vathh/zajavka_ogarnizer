package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.infrastructure.database.entity.ClosedAwayWorkEntity;
import pl.ogarnizer.infrastructure.database.entity.ClosedOrderEntity;

import java.util.Set;

@Repository
public interface ClosedOrderJpaRepository extends JpaRepository<ClosedOrderEntity, Integer> {

    @Query("""
            SELECT co FROM ClosedOrderEntity co
            WHERE co.creatingUser.userName LIKE %:keyword%
            OR (CAST (co.createdDate as string)) LIKE %:keyword%
            OR co.client.name LIKE %:keyword%
            OR co.description LIKE %:keyword%
            OR co.device LIKE %:keyword%
            OR co.additionalInfo LIKE %:keyword%
            OR co.updateInfo LIKE %:keyword%
            OR co.closingUser.userName LIKE %:keyword%
            OR (CAST (co.closedDate as string)) LIKE %:keyword%
            """)
    Page<ClosedOrderEntity> findAllByKeywordAndSort(final @Param("keyword") String keyword,
                                                       Pageable pageable);
}
