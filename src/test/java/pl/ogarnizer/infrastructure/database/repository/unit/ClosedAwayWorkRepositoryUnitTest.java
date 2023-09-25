package pl.ogarnizer.infrastructure.database.repository.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ogarnizer.domain.ClosedAwayWork;
import pl.ogarnizer.infrastructure.database.entity.ClosedAwayWorkEntity;
import pl.ogarnizer.infrastructure.database.repository.ClosedAwayWorkRepository;
import pl.ogarnizer.infrastructure.database.repository.jpa.ClosedAwayWorkJpaRepository;
import pl.ogarnizer.infrastructure.database.repository.mapper.ClosedAwayWorkEntityMapper;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.EntityFixtures;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClosedAwayWorkRepositoryUnitTest {

    @Mock
    private ClosedAwayWorkJpaRepository closedAwayWorkJpaRepository;
    @Mock
    private ClosedAwayWorkEntityMapper closedAwayWorkEntityMapper;

    @InjectMocks
    private ClosedAwayWorkRepository closedAwayWorkRepository;

    @Test
    void thatFindAllWorksCorrectly(){
        //given
        List<ClosedAwayWorkEntity> closedAwayWorkEntities = List.of(EntityFixtures.someClosedAwayWorkEntity1(),
                EntityFixtures.someClosedAwayWorkEntity1(), EntityFixtures.someClosedAwayWorkEntity1());
        when(closedAwayWorkJpaRepository.findAll()).thenReturn(closedAwayWorkEntities);
        when(closedAwayWorkEntityMapper.mapFromEntity(any())).thenReturn(DomainFixtures.someClosedAwayWork1());

        //when
        List<ClosedAwayWork> result = closedAwayWorkRepository.findAll();

        //then
        assertThat(result).isEqualTo(List.of(DomainFixtures.someClosedAwayWork1(),
                DomainFixtures.someClosedAwayWork1(), DomainFixtures.someClosedAwayWork1()));
    }
}
