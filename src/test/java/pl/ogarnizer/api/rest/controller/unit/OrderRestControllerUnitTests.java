package pl.ogarnizer.api.rest.controller.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ogarnizer.api.dto.OrderDTO;
import pl.ogarnizer.api.dto.mapper.OrderMapper;
import pl.ogarnizer.api.rest.controller.OrderRestController;
import pl.ogarnizer.api.rest.dto.OrdersDTO;
import pl.ogarnizer.business.OrderService;
import pl.ogarnizer.domain.Order;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.DtoFixtures;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderRestControllerUnitTests {

    @Mock
    private OrderService orderService;
    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderRestController orderRestController;

//    @Test
//    void thatGetOrdersWorksCorrectly(){
//        //given
//        List<Order> orders = List.of(DomainFixtures.someOrder1(), DomainFixtures.someOrder1(), DomainFixtures.someOrder1());
//        when(orderService.findOrders()).thenReturn(orders);
//        when(orderMapper.map(any())).thenReturn(DtoFixtures.someOrderDTO1());
//
//        //when
//        OrdersDTO result = orderRestController.getOrders();
//
//        //then
//        assertThat(result).isEqualTo(DtoFixtures.someOrdersDTO());
//    }

    @Test
    void thatOrderDetailsWorksCorrectly(){
        //given
        Integer orderId = 10;
        Order order = DomainFixtures.someOrder1();
        when(orderService.findOrderById(orderId)).thenReturn(order);
        when(orderMapper.map(order)).thenReturn(DtoFixtures.someOrderDTO1());

        //when
        OrderDTO result = orderRestController.orderDetails(orderId);

        //then
        assertThat(result).isEqualTo(DtoFixtures.someOrderDTO1());
    }
}
