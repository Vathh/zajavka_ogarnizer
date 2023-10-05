package pl.ogarnizer.api.rest.controller.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ogarnizer.api.dto.mapper.ClosedAwayWorkMapper;
import pl.ogarnizer.api.dto.mapper.ClosedOrderMapper;
import pl.ogarnizer.api.dto.mapper.ClosedServiceMapper;
import pl.ogarnizer.api.rest.controller.ClosedAwayWorkRestController;
import pl.ogarnizer.api.rest.controller.ClosedOrderRestController;
import pl.ogarnizer.api.rest.controller.ClosedServiceRestController;
import pl.ogarnizer.api.rest.dto.ClosedAwayWorksDTO;
import pl.ogarnizer.api.rest.dto.ClosedOrdersDTO;
import pl.ogarnizer.api.rest.dto.ClosedServicesDTO;
import pl.ogarnizer.business.ClosedAwayWorkService;
import pl.ogarnizer.business.ClosedOrderService;
import pl.ogarnizer.business.ClosedServiceService;
import pl.ogarnizer.domain.ClosedAwayWork;
import pl.ogarnizer.domain.ClosedOrder;
import pl.ogarnizer.domain.ClosedService;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.DtoFixtures;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClosedTasksRestControllerUnitTests {

    @Mock
    private ClosedAwayWorkService closedAwayWorkService;
    @Mock
    private ClosedOrderService closedOrderService;
    @Mock
    ClosedServiceService closedServiceService;
    @Mock
    private ClosedAwayWorkMapper closedAwayWorkMapper;
    @Mock
    private ClosedOrderMapper closedOrderMapper;
    @Mock
    private ClosedServiceMapper closedServiceMapper;

    @InjectMocks
    private ClosedAwayWorkRestController closedAwayWorkRestController;
    @InjectMocks
    private ClosedOrderRestController closedOrderRestController;
    @InjectMocks
    private ClosedServiceRestController closedServiceRestController;

//    @Test
//    void thatGetClosedAwayWorksWorksCorrectly(){
//        //given
//        List<ClosedAwayWork> closedAwayWorks = List.of(DomainFixtures.someClosedAwayWork1(),
//                DomainFixtures.someClosedAwayWork1(), DomainFixtures.someClosedAwayWork1());
//        when(closedAwayWorkService.findClosedAwayWorks()).thenReturn(closedAwayWorks);
//        when(closedAwayWorkMapper.map((ClosedAwayWork) any())).thenReturn(DtoFixtures.someClosedAwayWorkDTO1());
//
//        //when
//        ClosedAwayWorksDTO result = closedAwayWorkRestController.getClosedAwayWorks();
//
//        //then
//        assertThat(result).isEqualTo(DtoFixtures.someClosedAwayWorksDTO());
//    }

//    @Test
//    void thatGetClosedOrdersWorksCorrectly(){
//        //given
//        List<ClosedOrder> closedOrders = List.of(DomainFixtures.someClosedOrder1(),
//                DomainFixtures.someClosedOrder1(), DomainFixtures.someClosedOrder1());
//        when(closedOrderService.findClosedOrders()).thenReturn(closedOrders);
//        when(closedOrderMapper.map((ClosedOrder) any())).thenReturn(DtoFixtures.someClosedOrderDTO1());
//
//        //when
//        ClosedOrdersDTO result = closedOrderRestController.getClosedOrders();
//
//        //then
//        assertThat(result).isEqualTo(DtoFixtures.someClosedOrdersDTO());
//    }

//    @Test
//    void thatGetClosedServicesWorksCorrectly(){
//        //given
//        List<ClosedService> closedServices = List.of(DomainFixtures.someClosedService1(),
//                DomainFixtures.someClosedService1(), DomainFixtures.someClosedService1());
//        when(closedServiceService.findClosedServices()).thenReturn(closedServices);
//        when(closedServiceMapper.map((ClosedService) any())).thenReturn(DtoFixtures.someClosedServiceDTO1());
//
//        //when
//        ClosedServicesDTO result = closedServiceRestController.getClosedServices();
//
//        //then
//        assertThat(result).isEqualTo(DtoFixtures.someClosedServicesDTO());
//    }
}
