package pl.ogarnizer.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.ogarnizer.infrastructure.database.entity.StageEntity;
import pl.ogarnizer.integration.configuration.PersistenceContainerTestConfiguration;
import pl.ogarnizer.util.EntityFixtures;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))

public class StageJpaRepositoryTest {

    private StageJpaRepository stageJpaRepository;

    @Test
    void thatStageCanBeSavedCorrectly(){
        //given
        List<StageEntity> stageEntities = List.of(EntityFixtures.someStageEntity1(),
                EntityFixtures.someStageEntity2(), EntityFixtures.someStageEntity3(), EntityFixtures.someStageEntity4());
        stageJpaRepository.saveAllAndFlush(stageEntities);

        //when
        List<StageEntity> result = stageJpaRepository.findAll();

        //then
        assertThat(result).hasSize(4);
    }
}
