package pl.ogarnizer.integration.support;

import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.ogarnizer.api.dto.UserDTO;
import pl.ogarnizer.api.rest.controller.UserRestController;
import pl.ogarnizer.api.rest.dto.UsersDTO;

public interface UserControllerTestSupport {

    RequestSpecification requestSpecification();

    default UsersDTO getUsers(){
        return requestSpecification()
                .get(UserRestController.API_USER)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(UsersDTO.class);
    }

    default void addUser(final UserDTO userDTO){
        requestSpecification()
                .body(userDTO)
                .post(UserRestController.API_USER)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    default void deleteUser(final Integer userId){
        requestSpecification()
                .delete(UserRestController.API_USER + UserRestController.API_USER_ID,
                        userId)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
