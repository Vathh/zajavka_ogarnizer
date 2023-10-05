package pl.ogarnizer.infrastructure.database.repository.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ogarnizer.domain.ClosedService;
import pl.ogarnizer.infrastructure.database.entity.ClosedServiceEntity;
import pl.ogarnizer.infrastructure.database.repository.ClosedServiceRepository;
import pl.ogarnizer.infrastructure.database.repository.jpa.ClosedServiceJpaRepository;
import pl.ogarnizer.infrastructure.database.repository.mapper.ClosedServiceEntityMapper;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.EntityFixtures;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClosedServiceRepositoryUnitTest {

    @Mock
    private ClosedServiceJpaRepository closedServiceJpaRepository;
    @Mock
    private ClosedServiceEntityMapper closedServiceEntityMapper;

    @InjectMocks
    private ClosedServiceRepository closedServiceRepository;

    @Test
    void thatFindAllWorksCorrectly(){
        //given
        List<ClosedServiceEntity> closedServiceEntities = List.of(EntityFixtures.someClosedServiceEntity1(),
                EntityFixtures.someClosedServiceEntity1(), EntityFixtures.someClosedServiceEntity1());
        when(closedServiceJpaRepository.findAll()).thenReturn(closedServiceEntities);
        when(closedServiceEntityMapper.mapFromEntity(any())).thenReturn(DomainFixtures.someClosedService1());

        //when
        List<ClosedService> result = closedServiceRepository.findAll();

        //then
        assertThat(result).isEqualTo(List.of(DomainFixtures.someClosedService1(),
                DomainFixtures.someClosedService1(), DomainFixtures.someClosedService1()));
    }
}
