package pl.ogarnizer.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ogarnizer.api.dto.UserDTO;
import pl.ogarnizer.business.dao.RoleDAO;
import pl.ogarnizer.business.dao.UserDAO;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.User;
import pl.ogarnizer.domain.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final PasswordEncoder passwordEncoder;

    public User findUserByName(String userName){
        Optional<User> user = userDAO.findByUserName(userName);

        if(user.isEmpty()){
            throw new NotFoundException("Could not find user by user name: [%s]".formatted(userName));
        }
        return user.get();
    }

    public User findUserById(Integer userId){
        Optional<User> userById = userDAO.findByUserId(userId);
        if(userById.isEmpty()){
            throw new NotFoundException("Could not find User by id: [%s]".formatted(userId));
        }

        return userById.get();
    }

    public List<User> findUsers(){
        return userDAO.findUsers();
    }

    public void addUser(UserDTO userDTO){
        UserDTO userToAdd = userDTO.withPassword(passwordEncoder.encode(userDTO.getPassword()));

        User user = User.builder()
                .userName(userToAdd.getUserName())
                .login(userToAdd.getLogin())
                .password(userToAdd.getPassword())
                .roles(userDTO.getRoles().stream().map(role -> roleDAO.findRole(role).get()).toList())
                .active(true)
                .build();
        userDAO.addUser(user);
    }

    public void deleteUser(Integer userId) {
        userDAO.deleteUser(userId);
    }
}