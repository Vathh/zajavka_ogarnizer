package pl.ogarnizer.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.ogarnizer.infrastructure.database.entity.ClosedOrderEntity;
import pl.ogarnizer.integration.configuration.PersistenceContainerTestConfiguration;
import pl.ogarnizer.util.EntityFixtures;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClosedOrderJpaRepositoryTest {

    private ClosedOrderJpaRepository closedOrderJpaRepository;
    private ClientJpaRepository clientJpaRepository;
    private OgarnizerUserJpaRepository userJpaRepository;

    @Test
    void thatClosedOrderCanBeSavedCorrectly(){
        //given
        List<ClosedOrderEntity> closedOrderEntities = List.of(EntityFixtures.someClosedOrderEntity1(),
                EntityFixtures.someClosedOrderEntity2(), EntityFixtures.someClosedOrderEntity3());
        closedOrderEntities.forEach(entity -> clientJpaRepository.saveAndFlush(entity.getClient()));
        closedOrderEntities.forEach(entity -> userJpaRepository.saveAndFlush(entity.getCreatingUser()));
        closedOrderJpaRepository.saveAllAndFlush(closedOrderEntities);

        //when
        List<ClosedOrderEntity> result = closedOrderJpaRepository.findAll();

        //then
        assertThat(result).hasSize(3);
    }
}
