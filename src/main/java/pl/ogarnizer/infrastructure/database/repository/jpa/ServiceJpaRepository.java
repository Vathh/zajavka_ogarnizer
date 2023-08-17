package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.domain.Priority;
import pl.ogarnizer.domain.Stage;
import pl.ogarnizer.domain.User;
import pl.ogarnizer.infrastructure.database.entity.ServiceEntity;

import java.util.Set;

@Repository
public interface ServiceJpaRepository extends JpaRepository<ServiceEntity, Integer> {

    Set<ServiceEntity> findByCreatingUser(User user);

    @Query("""
            SELECT sv FROM ServiceEntity sv
            WHERE date_trunc('day', sv.createdDate) = :date            
            """)
    Set<ServiceEntity> findByCreatedDate(String date);

    Set<ServiceEntity> findByPriority(Priority priority);

    Set<ServiceEntity> findByClient(Client client);

    Set<ServiceEntity> findByStage(Stage stage);
}
