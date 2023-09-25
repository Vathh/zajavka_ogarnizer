package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.infrastructure.database.entity.AwayWorkEntity;
import pl.ogarnizer.infrastructure.database.entity.ClosedAwayWorkEntity;

import java.util.Set;

@Repository
public interface ClosedAwayWorkJpaRepository extends JpaRepository<ClosedAwayWorkEntity, Integer> {
    @Query("""
            SELECT caw FROM ClosedAwayWorkEntity caw
            WHERE caw.creatingUser.userName LIKE %:keyword%
            OR (CAST (caw.createdDate as string)) LIKE %:keyword%
            OR caw.client.name LIKE %:keyword%
            OR caw.description LIKE %:keyword%
            OR caw.place LIKE %:keyword%
            OR caw.device LIKE %:keyword%
            OR caw.additionalInfo LIKE %:keyword%
            OR caw.updateInfo LIKE %:keyword%
            OR caw.closingUser.userName LIKE %:keyword%
            OR (CAST (caw.closedDate as string)) LIKE %:keyword%
            """)
    Page<ClosedAwayWorkEntity> findAllByKeywordAndSort(final @Param("keyword") String keyword,
                                                 Pageable pageable);
}
