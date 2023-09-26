package pl.ogarnizer.infrastructure.database.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.infrastructure.database.entity.AwayWorkEntity;
import pl.ogarnizer.infrastructure.database.entity.ClientEntity;

import java.util.Optional;

@Repository
public interface ClientJpaRepository extends JpaRepository<ClientEntity, Integer> {

    Optional<ClientEntity> findByName(String name);

    @Query("""
            SELECT clt FROM ClientEntity clt
            WHERE clt.name LIKE %:keyword%
            OR clt.address LIKE %:keyword%
            OR clt.nip LIKE %:keyword%
            OR clt.phoneNumber LIKE %:keyword%
            """)
    Page<ClientEntity> findAllByKeywordAndSort(final @Param("keyword") String keyword,
                                                 Pageable pageable);
}
