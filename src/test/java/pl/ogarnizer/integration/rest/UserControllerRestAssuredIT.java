package pl.ogarnizer.integration.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.ogarnizer.api.dto.UserDTO;
import pl.ogarnizer.api.rest.dto.UsersDTO;
import pl.ogarnizer.integration.configuration.RestAssuredIntegrationTestBase;
import pl.ogarnizer.integration.support.UserControllerTestSupport;
import pl.ogarnizer.util.DtoFixtures;

public class UserControllerRestAssuredIT
        extends RestAssuredIntegrationTestBase
        implements UserControllerTestSupport {

    @Test
    void thatUserListCanBeRetrievedCorrectly(){
        //given
        UserDTO userDTO = DtoFixtures.someUserDTO4();
        UserDTO userDTO2 = DtoFixtures.someUserDTO5();
        //when
        addUser(userDTO);
        addUser(userDTO2);

        UsersDTO users = getUsers();

        //then
        Assertions.assertThat(users.getUsers())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("userId", "password")
                .contains(userDTO, userDTO2);
    }

    @Test
    void thatDeletingUserWorksCorrectly(){
        //when
        UsersDTO users = getUsers();
        users.getUsers().forEach(user -> deleteUser(user.getUserId()));
        UsersDTO usersToCompare = getUsers();

        //then
        Assertions.assertThat(usersToCompare.getUsers()).hasSize(0);
    }
}
