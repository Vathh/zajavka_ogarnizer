package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.domain.Priority;
import pl.ogarnizer.domain.Stage;
import pl.ogarnizer.domain.User;
import pl.ogarnizer.infrastructure.database.entity.AwayWorkEntity;

import java.util.Set;

@Repository
public interface AwayWorkJpaRepository extends JpaRepository<AwayWorkEntity, Integer> {

    Set<AwayWorkEntity> findByCreatingUser(User user);

    @Query("""
            SELECT aw FROM AwayWorkEntity aw
            WHERE date_trunc('day', aw.createdDate) = :date         
            """)
    Set<AwayWorkEntity> findByCreatedDate(final @Param("date") String date);

    Set<AwayWorkEntity> findByPriority(Priority priority);

    Set<AwayWorkEntity> findByClient(Client client);

    Set<AwayWorkEntity> findByStage(Stage stage);
}
