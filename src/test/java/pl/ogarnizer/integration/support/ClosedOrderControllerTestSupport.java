package pl.ogarnizer.integration.support;

import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.ogarnizer.api.rest.controller.ClosedOrderRestController;
import pl.ogarnizer.api.rest.dto.ClosedOrdersDTO;

public interface ClosedOrderControllerTestSupport {

    RequestSpecification requestSpecification();

    default ClosedOrdersDTO getClosedOrders(){
        return requestSpecification()
                .get(ClosedOrderRestController.API_CLOSED_ORDER)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(ClosedOrdersDTO.class);
    }

    default void deleteClosedOrder(final Integer orderId){
        requestSpecification()
                .delete(ClosedOrderRestController.API_CLOSED_ORDER + ClosedOrderRestController.API_CLOSED_ORDER_ID,
                        orderId)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
