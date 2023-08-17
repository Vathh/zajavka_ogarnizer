package pl.ogarnizer.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.ogarnizer.infrastructure.database.entity.OrderEntity;
import pl.ogarnizer.integration.configuration.PersistenceContainerTestConfiguration;
import pl.ogarnizer.util.EntityFixtures;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))

public class OrderJpaRepositoryTest {

    private OrderJpaRepository orderJpaRepository;
    private ClientJpaRepository clientJpaRepository;
    private OgarnizerUserJpaRepository userJpaRepository;

    @Test
    void thatOrderCanBeSavedCorrectly(){
        //given
        List<OrderEntity> orderEntities = List.of(EntityFixtures.someOrderEntity1(),
                EntityFixtures.someOrderEntity2(), EntityFixtures.someOrderEntity3());
        orderEntities.forEach(orderEntity -> clientJpaRepository.saveAndFlush(orderEntity.getClient()));
        orderEntities.forEach(orderEntity -> userJpaRepository.saveAndFlush(orderEntity.getCreatingUser()));
        orderJpaRepository.saveAllAndFlush(orderEntities);

        //when
        List<OrderEntity> result = orderJpaRepository.findAll();

        //then
        assertThat(result).hasSize(3);
    }
}
