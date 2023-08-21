package pl.ogarnizer.integration.support;

import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.ogarnizer.api.dto.AwayWorkDTO;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.UpdateTaskDTO;
import pl.ogarnizer.api.rest.controller.AwayWorkRestController;
import pl.ogarnizer.api.rest.controller.ClientRestController;
import pl.ogarnizer.api.rest.dto.AwayWorksDTO;

public interface AwayWorkControllerTestSupport {

    RequestSpecification requestSpecification();

    default AwayWorksDTO getAwayWorks(){
        return requestSpecification()
                .get(AwayWorkRestController.API_AWAY_WORK)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(AwayWorksDTO.class);
    }

    default AwayWorkDTO awayWorkDetails(final Integer awayWorkId){
        return requestSpecification()
                .get(AwayWorkRestController.API_AWAY_WORK + AwayWorkRestController.API_AWAY_WORK_ID, awayWorkId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(AwayWorkDTO.class);
    }

    default AwayWorksDTO addAwayWork(final TaskDTO taskDTO){
        return requestSpecification()
                .body(taskDTO)
                .post(AwayWorkRestController.API_AWAY_WORK)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(AwayWorksDTO.class);
    }

    default void addClient(final ClientDTO clientDTO){
        requestSpecification()
                .body(clientDTO)
                .post(ClientRestController.API_CLIENT)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    default void updateAwayWork(final Integer awayWorkId, final UpdateTaskDTO updateTaskDTO){
        requestSpecification()
                .body(updateTaskDTO)
                .put(AwayWorkRestController.API_AWAY_WORK + AwayWorkRestController.API_AWAY_WORK_ID, awayWorkId)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    default void deleteAwayWork(final Integer awayWorkId, final Boolean success, final String closingUserName){
        requestSpecification()
                .delete(AwayWorkRestController.API_AWAY_WORK + AwayWorkRestController.API_DELETE_AWAY_WORK,
                        awayWorkId, success, closingUserName)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    default void loadRandomAwayWorks(){
        requestSpecification()
                .get(AwayWorkRestController.API_AWAY_WORK + AwayWorkRestController.LOAD_RANDOM_AWAY_WORKS)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
