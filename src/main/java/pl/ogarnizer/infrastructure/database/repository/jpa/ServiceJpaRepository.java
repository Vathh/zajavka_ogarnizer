package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.infrastructure.database.entity.AwayWorkEntity;
import pl.ogarnizer.infrastructure.database.entity.ServiceEntity;

import java.util.Set;

@Repository
public interface ServiceJpaRepository extends JpaRepository<ServiceEntity, Integer> {

    @Query("""
            SELECT sv FROM ServiceEntity sv
            WHERE sv.creatingUser.userName LIKE %:keyword%
            OR (CAST (sv.createdDate as string)) LIKE %:keyword%
            OR sv.client.name LIKE %:keyword%
            OR sv.description LIKE %:keyword%
            OR sv.device LIKE %:keyword%
            OR sv.additionalInfo LIKE %:keyword%
            OR sv.updateInfo LIKE %:keyword%
            """)
    Page<ServiceEntity> findAllByKeywordAndSort(final @Param("keyword") String keyword,
                                                 Pageable pageable);

    @Query("""
            SELECT COUNT(sv) FROM ServiceEntity sv
            WHERE sv.priority.name=:priorityName
            """)
    long countByPriorityName(final @Param("priorityName") String priorityName);

    @Query("""
            SELECT COUNT(sv) FROM ServiceEntity sv
            WHERE sv.stage.name=:stageName
            """)
    long countByStageName(final @Param("stageName") String stageName);
}
