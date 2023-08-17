package pl.ogarnizer.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.ogarnizer.infrastructure.database.entity.ClosedServiceEntity;
import pl.ogarnizer.integration.configuration.PersistenceContainerTestConfiguration;
import pl.ogarnizer.util.EntityFixtures;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))

public class ClosedServiceJpaRepositoryTest {

    private ClosedServiceJpaRepository closedServiceJpaRepository;
    private ClientJpaRepository clientJpaRepository;
    private OgarnizerUserJpaRepository userJpaRepository;

    @Test
    void thatClosedServiceCanBeSavedCorrectly(){
        //given
        List<ClosedServiceEntity> closedServiceEntities = List.of(EntityFixtures.someClosedServiceEntity1(),
                EntityFixtures.someClosedServiceEntity2(), EntityFixtures.someClosedServiceEntity3());
        closedServiceEntities.forEach(entity -> clientJpaRepository.saveAndFlush(entity.getClient()));
        closedServiceEntities.forEach(entity -> userJpaRepository.saveAndFlush(entity.getCreatingUser()));
        closedServiceJpaRepository.saveAllAndFlush(closedServiceEntities);

        //when
        List<ClosedServiceEntity> result = closedServiceJpaRepository.findAll();

        //then
        assertThat(result).hasSize(3);
    }
}
