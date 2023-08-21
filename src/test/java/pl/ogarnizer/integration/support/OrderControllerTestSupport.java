package pl.ogarnizer.integration.support;

import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.dto.OrderDTO;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.UpdateTaskDTO;
import pl.ogarnizer.api.rest.controller.ClientRestController;
import pl.ogarnizer.api.rest.controller.OrderRestController;
import pl.ogarnizer.api.rest.dto.OrdersDTO;

public interface OrderControllerTestSupport {

    RequestSpecification requestSpecification();

    default OrdersDTO getOrders(){
        return requestSpecification()
                .get(OrderRestController.API_ORDER)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(OrdersDTO.class);
    }

    default OrderDTO orderDetails(final Integer orderId){
        return requestSpecification()
                .get(OrderRestController.API_ORDER + OrderRestController.API_ORDER_ID, orderId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(OrderDTO.class);
    }

    default OrdersDTO addOrder(final TaskDTO taskDTO){
        return requestSpecification()
                .body(taskDTO)
                .post(OrderRestController.API_ORDER)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(OrdersDTO.class);
    }

    default void addClient(final ClientDTO clientDTO){
        requestSpecification()
                .body(clientDTO)
                .post(ClientRestController.API_CLIENT)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    default void updateOrder(final Integer orderId, final UpdateTaskDTO updateTaskDTO){
        requestSpecification()
                .body(updateTaskDTO)
                .put(OrderRestController.API_ORDER + OrderRestController.API_ORDER_ID, orderId)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    default void deleteOrder(final Integer orderId, final Boolean success, final String closingUserName){
        requestSpecification()
                .delete(OrderRestController.API_ORDER + OrderRestController.API_DELETE_ORDER,
                        orderId, success, closingUserName)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    default void loadRandomOrders(){
        requestSpecification()
                .get(OrderRestController.API_ORDER + OrderRestController.LOAD_RANDOM_ORDERS)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
