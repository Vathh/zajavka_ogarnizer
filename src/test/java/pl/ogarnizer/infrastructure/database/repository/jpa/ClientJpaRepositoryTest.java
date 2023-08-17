package pl.ogarnizer.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.ogarnizer.infrastructure.database.entity.ClientEntity;
import pl.ogarnizer.integration.configuration.PersistenceContainerTestConfiguration;
import pl.ogarnizer.util.EntityFixtures;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientJpaRepositoryTest {

    private ClientJpaRepository clientJpaRepository;

    @Test
    void thatClientCanBeSavedCorrectly(){
        //given
        List<ClientEntity> clientEntities = List.of(EntityFixtures.someClientEntity1(),
                EntityFixtures.someClientEntity2(), EntityFixtures.someClientEntity3());
        clientJpaRepository.saveAllAndFlush(clientEntities);

        //when
        List<ClientEntity> result = clientJpaRepository.findAll();
        System.out.println(result);

        //then
        assertThat(result).hasSize(3);
    }
}
