package pl.ogarnizer.api.rest.controller.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ogarnizer.api.dto.AwayWorkDTO;
import pl.ogarnizer.api.dto.mapper.AwayWorkMapper;
import pl.ogarnizer.api.dto.mapper.UserMapper;
import pl.ogarnizer.api.rest.controller.AwayWorkRestController;
import pl.ogarnizer.api.rest.controller.UserRestController;
import pl.ogarnizer.api.rest.dto.AwayWorksDTO;
import pl.ogarnizer.api.rest.dto.UsersDTO;
import pl.ogarnizer.business.AwayWorkService;
import pl.ogarnizer.business.UserService;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.User;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.DtoFixtures;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRestControllerUnitTests {

    @Mock
    private UserService userService;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserRestController userRestController;

    @Test
    void thatGetUsersWorksCorrectly(){
        //given
        List<User> awayWorkList = List.of(DomainFixtures.someUser1(),
                DomainFixtures.someUser1(),DomainFixtures.someUser1());
        when(userService.findUsers()).thenReturn(awayWorkList);
        when(userMapper.map(any())).thenReturn(DtoFixtures.someUserDTO1());

        //when
        UsersDTO result = userRestController.getUsers();

        //then
        assertThat(result).isEqualTo(DtoFixtures.someUsersDTO());
    }
}
