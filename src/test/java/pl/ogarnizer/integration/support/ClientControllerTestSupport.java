package pl.ogarnizer.integration.support;

import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.rest.controller.ClientRestController;
import pl.ogarnizer.api.rest.dto.ClientsDTO;

public interface ClientControllerTestSupport {

    RequestSpecification requestSpecification();

    default ClientsDTO getClients(){
        return requestSpecification()
                .get(ClientRestController.API_CLIENT)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(ClientsDTO.class);
    }

    default ClientDTO clientDetails(final Integer clientId){
        return requestSpecification()
                .get(ClientRestController.API_CLIENT + ClientRestController.API_CLIENT_ID, clientId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(ClientDTO.class);
    }

    default void addClient(final ClientDTO clientDTO){
        requestSpecification()
                .body(clientDTO)
                .post(ClientRestController.API_CLIENT)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    default void deleteClient(final Integer clientId){
        requestSpecification()
                .delete(ClientRestController.API_CLIENT + ClientRestController.API_CLIENT_ID,
                        clientId)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    default void loadRandomClients(){
        requestSpecification()
                .get(ClientRestController.API_CLIENT + ClientRestController.LOAD_RANDOM_CLIENTS)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
