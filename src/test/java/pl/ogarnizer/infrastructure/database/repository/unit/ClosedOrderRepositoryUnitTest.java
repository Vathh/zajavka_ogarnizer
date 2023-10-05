package pl.ogarnizer.infrastructure.database.repository.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ogarnizer.domain.ClosedOrder;
import pl.ogarnizer.infrastructure.database.entity.ClosedOrderEntity;
import pl.ogarnizer.infrastructure.database.repository.ClosedOrderRepository;
import pl.ogarnizer.infrastructure.database.repository.jpa.ClosedOrderJpaRepository;
import pl.ogarnizer.infrastructure.database.repository.mapper.ClosedOrderEntityMapper;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.EntityFixtures;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClosedOrderRepositoryUnitTest {

    @Mock
    private ClosedOrderJpaRepository closedOrderJpaRepository;
    @Mock
    private ClosedOrderEntityMapper closedOrderEntityMapper;

    @InjectMocks
    private ClosedOrderRepository closedOrderRepository;

    @Test
    void thatFindAllWorksCorrectly(){
        //given
        List<ClosedOrderEntity> closedOrderEntities = List.of(EntityFixtures.someClosedOrderEntity1(),
                EntityFixtures.someClosedOrderEntity1(), EntityFixtures.someClosedOrderEntity1());
        when(closedOrderJpaRepository.findAll()).thenReturn(closedOrderEntities);
        when(closedOrderEntityMapper.mapFromEntity(any())).thenReturn(DomainFixtures.someClosedOrder1());

        //when
        List<ClosedOrder> result = closedOrderRepository.findAll();

        //then
        assertThat(result).isEqualTo(List.of(DomainFixtures.someClosedOrder1(),
                DomainFixtures.someClosedOrder1(), DomainFixtures.someClosedOrder1()));
    }
}
