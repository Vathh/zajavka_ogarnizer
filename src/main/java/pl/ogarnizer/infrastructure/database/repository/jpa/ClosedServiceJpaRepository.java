package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.infrastructure.database.entity.ClosedAwayWorkEntity;
import pl.ogarnizer.infrastructure.database.entity.ClosedServiceEntity;

import java.util.Set;

@Repository
public interface ClosedServiceJpaRepository extends JpaRepository<ClosedServiceEntity, Integer> {

    @Query("""
            SELECT csv FROM ClosedServiceEntity csv
            WHERE csv.creatingUser.userName LIKE %:keyword%
            OR (CAST (csv.createdDate as string)) LIKE %:keyword%
            OR csv.client.name LIKE %:keyword%
            OR csv.description LIKE %:keyword%
            OR csv.device LIKE %:keyword%
            OR csv.additionalInfo LIKE %:keyword%
            OR csv.updateInfo LIKE %:keyword%
            OR csv.closingUser.userName LIKE %:keyword%
            OR (CAST (csv.closedDate as string)) LIKE %:keyword%
            """)
    Page<ClosedServiceEntity> findAllByKeywordAndSort(final @Param("keyword") String keyword,
                                                       Pageable pageable);
}
