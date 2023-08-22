package pl.ogarnizer.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.ogarnizer.business.dao.RoleDAO;
import pl.ogarnizer.business.dao.UserDAO;
import pl.ogarnizer.domain.User;
import pl.ogarnizer.util.DomainFixtures;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTests {

    @Mock
    private UserDAO userDAO;
    @Mock
    private RoleDAO roleDAO;
    @InjectMocks
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void thatFindUserByNameWorksCorrectly(){
        //given
        String name = "name";
        User user = DomainFixtures.someUser1();
        when(userDAO.findByUserName(name)).thenReturn(Optional.of(user));

        //when
        User result = userService.findUserByName(name);

        //then
        assertThat(result).isEqualTo(DomainFixtures.someUser1());
    }

    @Test
    void thatFindUserByIdWorksCorrectly(){
        //given
        Integer userId = 1;
        User user = DomainFixtures.someUser1();
        when(userDAO.findByUserId(userId)).thenReturn(Optional.of(user));

        //when
        User result = userService.findUserById(userId);

        //then
        assertThat(result).isEqualTo(DomainFixtures.someUser1());
    }

    @Test
    void thatFindUsersWorksCorrectly(){
        //given
        List<User> users = List.of(DomainFixtures.someUser1(), DomainFixtures.someUser1(), DomainFixtures.someUser1());
        when(userDAO.findUsers()).thenReturn(users);

        //when
        List<User> result = userService.findUsers();

        //then
        assertThat(result).isEqualTo(List.of(DomainFixtures.someUser1(), DomainFixtures.someUser1(),
                DomainFixtures.someUser1()));
    }


}
