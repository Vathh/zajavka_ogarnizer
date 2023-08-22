package pl.ogarnizer.integration.support;

import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.ogarnizer.api.rest.controller.ClosedAwayWorkRestController;
import pl.ogarnizer.api.rest.dto.ClosedAwayWorksDTO;

public interface ClosedAwayWorkControllerTestSupport {

    RequestSpecification requestSpecification();

    default ClosedAwayWorksDTO getClosedAwayWorks(){
        return requestSpecification()
                .get(ClosedAwayWorkRestController.API_CLOSED_AWAY_WORK)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(ClosedAwayWorksDTO.class);
    }

    default void deleteClosedAwayWork(final Integer awayWorkId){
        requestSpecification()
                .delete(ClosedAwayWorkRestController.API_CLOSED_AWAY_WORK + ClosedAwayWorkRestController.API_CLOSED_AWAY_WORK_ID,
                        awayWorkId)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
