package pl.ogarnizer.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ogarnizer.business.dao.AwayWorkDAO;
import pl.ogarnizer.business.dao.ClosedAwayWorkDAO;
import pl.ogarnizer.business.dao.ClosedOrderDAO;
import pl.ogarnizer.business.dao.ClosedServiceDAO;
import pl.ogarnizer.domain.*;
import pl.ogarnizer.util.DomainFixtures;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClosedTasksServicesUnitTests {

    @Mock
    private ClosedAwayWorkDAO closedAwayWorkDAO;
    @Mock
    private ClosedOrderDAO closedOrderDAO;
    @Mock
    private ClosedServiceDAO closedServiceDAO;

    @InjectMocks
    private ClosedAwayWorkService closedAwayWorkService;
    @InjectMocks
    private ClosedOrderService closedOrderService;
    @InjectMocks
    private ClosedServiceService closedServiceService;

    @Test
    void thatFindClosedAwayWorksWorksCorrectly(){
        //given
        List<ClosedAwayWork> closedAwayWorks = List.of(DomainFixtures.someClosedAwayWork1(),
                DomainFixtures.someClosedAwayWork1(), DomainFixtures.someClosedAwayWork1());
        when(closedAwayWorkDAO.findAll()).thenReturn(closedAwayWorks);

        //when
        List<ClosedAwayWork> result = closedAwayWorkService.findClosedAwayWorks();

        //then
        assertThat(result).isEqualTo(List.of(DomainFixtures.someClosedAwayWork1(),
                DomainFixtures.someClosedAwayWork1(), DomainFixtures.someClosedAwayWork1()));
    }

    @Test
    void thatFindClosedOrdersWorksCorrectly(){
        //given
        List<ClosedOrder> closedOrders = List.of(DomainFixtures.someClosedOrder1(),
                DomainFixtures.someClosedOrder1(), DomainFixtures.someClosedOrder1());
        when(closedOrderDAO.findAll()).thenReturn(closedOrders);

        //when
        List<ClosedOrder> result = closedOrderService.findClosedOrders();

        //then
        assertThat(result).isEqualTo(List.of(DomainFixtures.someClosedOrder1(),
                DomainFixtures.someClosedOrder1(), DomainFixtures.someClosedOrder1()));
    }

    @Test
    void thatFindClosedServicesWorksCorrectly(){
        //given
        List<ClosedService> closedServices = List.of(DomainFixtures.someClosedService1(),
                DomainFixtures.someClosedService1(), DomainFixtures.someClosedService1());
        when(closedServiceDAO.findAll()).thenReturn(closedServices);

        //when
        List<ClosedService> result = closedServiceService.findClosedServices();

        //then
        assertThat(result).isEqualTo(List.of(DomainFixtures.someClosedService1(),
                DomainFixtures.someClosedService1(), DomainFixtures.someClosedService1()));
    }

}
