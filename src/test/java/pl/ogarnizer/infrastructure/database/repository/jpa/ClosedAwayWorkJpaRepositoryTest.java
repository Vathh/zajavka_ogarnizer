package pl.ogarnizer.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.ogarnizer.infrastructure.database.entity.ClosedAwayWorkEntity;
import pl.ogarnizer.integration.configuration.PersistenceContainerTestConfiguration;
import pl.ogarnizer.util.EntityFixtures;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClosedAwayWorkJpaRepositoryTest {

    private ClosedAwayWorkJpaRepository closedAwayWorkJpaRepository;
    private ClientJpaRepository clientJpaRepository;
    private OgarnizerUserJpaRepository userJpaRepository;

    @Test
    void thatClosedAwayWorkCanBeSavedCorrectly(){
        //given
        List<ClosedAwayWorkEntity> closedAwayWorkEntities = List.of(EntityFixtures.someClosedAwayWorkEntity1(),
                EntityFixtures.someClosedAwayWorkEntity2(), EntityFixtures.someClosedAwayWorkEntity3());
        closedAwayWorkEntities.forEach(entity -> clientJpaRepository.saveAndFlush(entity.getClient()));
        closedAwayWorkEntities.forEach(entity -> userJpaRepository.saveAndFlush(entity.getCreatingUser()));
        closedAwayWorkJpaRepository.saveAllAndFlush(closedAwayWorkEntities);

        //when
        List<ClosedAwayWorkEntity> result = closedAwayWorkJpaRepository.findAll();

        //then
        assertThat(result).hasSize(3);
    }
}
