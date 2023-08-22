package pl.ogarnizer.integration.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.dto.ClosedOrderDTO;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.rest.dto.OrdersDTO;
import pl.ogarnizer.api.rest.dto.ClosedOrdersDTO;
import pl.ogarnizer.integration.configuration.RestAssuredIntegrationTestBase;
import pl.ogarnizer.integration.support.OrderControllerTestSupport;
import pl.ogarnizer.integration.support.ClosedOrderControllerTestSupport;
import pl.ogarnizer.util.DtoFixtures;

public class ClosedOrderControllerRestAssuredIT
        extends RestAssuredIntegrationTestBase
        implements ClosedOrderControllerTestSupport, OrderControllerTestSupport {

    @Test
    void thatClosedOrderListCanBeRetrievedCorrectly(){
        //given
        TaskDTO taskDTO = DtoFixtures.someTaskDTO1();
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();

        TaskDTO taskDTO2 = DtoFixtures.someTaskDTO2();
        ClientDTO clientDTO2 = DtoFixtures.someClientDTO2();

        ClosedOrderDTO closedOrderDTO = DtoFixtures.someClosedOrderDTO1().withUpdateInfo(null);
        ClosedOrderDTO closedOrderDTO2 = DtoFixtures.someClosedOrderDTO2().withUpdateInfo(null);
        //when
        addClient(clientDTO);
        addOrder(taskDTO);
        addClient(clientDTO2);
        addOrder(taskDTO2);

        OrdersDTO orders = getOrders();
        orders.getOrders().forEach(order -> deleteOrder(order.getOrderId(),
                true, order.getCreatingUserName()));

        ClosedOrdersDTO closedOrders = getClosedOrders();

        //then
        Assertions.assertThat(closedOrders.getClosedOrders())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("closedOrderId", "createdDate", "closedDate")
                .containsExactlyInAnyOrder(closedOrderDTO, closedOrderDTO2);
    }

    @Test
    void thatDeletingClosedOrderWorksCorrectly(){
        //given
        TaskDTO taskDTO = DtoFixtures.someTaskDTO1();
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();

        TaskDTO taskDTO2 = DtoFixtures.someTaskDTO2();
        ClientDTO clientDTO2 = DtoFixtures.someClientDTO2();
        //when
        addClient(clientDTO);
        addOrder(taskDTO);
        addClient(clientDTO2);
        addOrder(taskDTO2);

        OrdersDTO orders = getOrders();
        orders.getOrders().forEach(order -> deleteOrder(order.getOrderId(),
                true, order.getCreatingUserName()));
        Integer closedOrderId = getClosedOrders().getClosedOrders().get(0).getClosedOrderId();
        deleteClosedOrder(closedOrderId);

        ClosedOrdersDTO closedOrders = getClosedOrders();

        //then
        Assertions.assertThat(closedOrders.getClosedOrders()).hasSize(1);
    }
}
