package pl.ogarnizer.integration.support;

import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.ogarnizer.api.rest.controller.ClosedServiceRestController;
import pl.ogarnizer.api.rest.dto.ClosedServicesDTO;

public interface ClosedServiceControllerTestSupport {

    RequestSpecification requestSpecification();

    default ClosedServicesDTO getClosedServices(){
        return requestSpecification()
                .get(ClosedServiceRestController.API_CLOSED_SERVICE)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(ClosedServicesDTO.class);
    }

    default void deleteClosedService(final Integer serviceId){
        requestSpecification()
                .delete(ClosedServiceRestController.API_CLOSED_SERVICE + ClosedServiceRestController.API_CLOSED_SERVICE_ID,
                        serviceId)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
