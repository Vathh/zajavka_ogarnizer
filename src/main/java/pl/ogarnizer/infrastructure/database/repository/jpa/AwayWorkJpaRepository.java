package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.infrastructure.database.entity.AwayWorkEntity;

import java.util.Set;

@Repository
public interface AwayWorkJpaRepository extends JpaRepository<AwayWorkEntity, Integer> {

    @Query("""
            SELECT aw FROM AwayWorkEntity aw
            WHERE date_trunc('day', aw.createdDate) = :date         
            """)
    Set<AwayWorkEntity> findByCreatedDate(final @Param("date") String date);

    @Query("""
            SELECT aw FROM AwayWorkEntity aw
            WHERE aw.creatingUser.userName LIKE %:keyword%
            OR (CAST (aw.createdDate as string)) LIKE %:keyword%
            OR aw.client.name LIKE %:keyword%
            OR aw.description LIKE %:keyword%
            OR aw.place LIKE %:keyword%
            OR aw.device LIKE %:keyword%
            OR aw.additionalInfo LIKE %:keyword%
            OR aw.updateInfo LIKE %:keyword%
            """)
    Page<AwayWorkEntity> findAllByKeywordAndSort(final @Param("keyword") String keyword,
                                                 Pageable pageable);
    @Query("""
            SELECT COUNT(aw) FROM AwayWorkEntity aw
            WHERE aw.priority.name=:priorityName
            """)
    long countByPriorityName(final @Param("priorityName") String priorityName);

    @Query("""
            SELECT COUNT(aw) FROM AwayWorkEntity aw
            WHERE aw.stage.name=:stageName
            """)
    long countByStageName(final @Param("stageName") String stageName);
}



