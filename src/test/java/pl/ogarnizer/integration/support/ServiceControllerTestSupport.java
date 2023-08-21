package pl.ogarnizer.integration.support;

import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.ogarnizer.api.dto.ServiceDTO;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.UpdateTaskDTO;
import pl.ogarnizer.api.rest.controller.ServiceRestController;
import pl.ogarnizer.api.rest.controller.ClientRestController;
import pl.ogarnizer.api.rest.dto.ServicesDTO;
import pl.ogarnizer.api.rest.dto.ServicesDTO;

public interface ServiceControllerTestSupport {

    RequestSpecification requestSpecification();

    default ServicesDTO getServices(){
        return requestSpecification()
                .get(ServiceRestController.API_SERVICE)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(ServicesDTO.class);
    }

    default ServiceDTO serviceDetails(final Integer serviceId){
        return requestSpecification()
                .get(ServiceRestController.API_SERVICE + ServiceRestController.API_SERVICE_ID, serviceId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(ServiceDTO.class);
    }

    default ServicesDTO addService(final TaskDTO taskDTO){
        return requestSpecification()
                .body(taskDTO)
                .post(ServiceRestController.API_SERVICE)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(ServicesDTO.class);
    }

    default void addClient(final ClientDTO clientDTO){
        requestSpecification()
                .body(clientDTO)
                .post(ClientRestController.API_CLIENT)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    default void updateService(final Integer serviceId, final UpdateTaskDTO updateTaskDTO){
        requestSpecification()
                .body(updateTaskDTO)
                .put(ServiceRestController.API_SERVICE + ServiceRestController.API_SERVICE_ID, serviceId)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    default void deleteService(final Integer serviceId, final Boolean success, final String closingUserName){
        requestSpecification()
                .delete(ServiceRestController.API_SERVICE + ServiceRestController.API_DELETE_SERVICE,
                        serviceId, success, closingUserName)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    default void loadRandomServices(){
        requestSpecification()
                .get(ServiceRestController.API_SERVICE + ServiceRestController.LOAD_RANDOM_SERVICES)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
