package pl.ogarnizer.integration.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.ogarnizer.api.dto.OrderDTO;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.UpdateTaskDTO;
import pl.ogarnizer.api.rest.dto.OrdersDTO;
import pl.ogarnizer.integration.configuration.RestAssuredIntegrationTestBase;
import pl.ogarnizer.integration.support.OrderControllerTestSupport;
import pl.ogarnizer.integration.support.OrderControllerTestSupport;
import pl.ogarnizer.integration.support.WireMockTestSupport;
import pl.ogarnizer.util.DtoFixtures;

public class OrderControllerRestAssuredIT
        extends RestAssuredIntegrationTestBase
        implements OrderControllerTestSupport, WireMockTestSupport {

    @Test
    void thatLoadRandomDataWorksCorrectly(){
        //given
        stubForLoadOrders(wireMockServer);

        //when
        loadRandomOrders();
        OrdersDTO orders = getOrders();

        //then
        Assertions.assertThat(orders.getOrders()).hasSize(5);
    }

    @Test
    void thatOrderListCanBeRetrievedCorrectly(){
        //given
        TaskDTO taskDTO = DtoFixtures.someTaskDTO1();
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();

        TaskDTO taskDTO2 = DtoFixtures.someTaskDTO2();
        ClientDTO clientDTO2 = DtoFixtures.someClientDTO2();

        OrderDTO orderDTO = DtoFixtures.someOrderDTO1().withUpdateInfo(null);
        OrderDTO orderDTO2 = DtoFixtures.someOrderDTO2().withUpdateInfo(null);
        //when
        addClient(clientDTO);
        addOrder(taskDTO);
        addClient(clientDTO2);
        addOrder(taskDTO2);

        OrdersDTO orders = getOrders();

        //then
        Assertions.assertThat(orders.getOrders())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("orderId", "createdDate", "stageName")
                .containsExactlyInAnyOrder(orderDTO, orderDTO2);
    }

    @Test
    void thatOrderDetailsCanBeRetrievedCorrectly(){
        //given
        TaskDTO taskDTO = DtoFixtures.someTaskDTO1();
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();
        OrderDTO orderToCompare = DtoFixtures.someOrderDTO1().withUpdateInfo(null);

        //when
        addClient(clientDTO);
        addOrder(taskDTO);
        OrdersDTO orders = getOrders();
        Integer orderId = orders.getOrders().get(0).getOrderId();

        OrderDTO orderDTO = orderDetails(orderId);

        //then
        Assertions.assertThat(orderDTO)
                .isEqualTo(orderToCompare);
    }

    @Test
    void thatUpdatingOrderWorksCorrectly(){
        //given
        TaskDTO taskDTO = DtoFixtures.someTaskDTO1();
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();
        OrderDTO orderToCompare = DtoFixtures.someOrderDTO1()
                .withPriorityName("high")
                .withUpdateInfo("inne informacje o aktualizacji")
                .withStageName("to_invoice");

        //when
        addClient(clientDTO);
        addOrder(taskDTO);
        OrdersDTO orders = getOrders();
        Integer orderId = orders.getOrders().get(0).getOrderId();
        UpdateTaskDTO updateTaskDTO = DtoFixtures.someUpdateTaskDTO1();

        updateOrder(orderId, updateTaskDTO);
        OrderDTO orderDTO = orderDetails(orderId);

        //then
        Assertions.assertThat(orderDTO)
                .isEqualTo(orderToCompare);
    }

    @Test
    void thatDeletingOrderWorksCorrectly(){
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

        OrderDTO orderToDelete = getOrders().getOrders().get(0);
        deleteOrder(orderToDelete.getOrderId(), true, "Wojtek");
        OrdersDTO orders = getOrders();

        //then
        Assertions.assertThat(orders.getOrders()).hasSize(1);
    }
}
