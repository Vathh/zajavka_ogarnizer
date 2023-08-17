package pl.ogarnizer.business.dao;

import pl.ogarnizer.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    Optional<User> findByUserName(String name);
    List<User> findUsers();
    void addUser(User user);
    Optional<User> findByUserId(Integer userId);
    void deleteUser(Integer userId);
}
