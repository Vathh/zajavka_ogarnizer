package pl.ogarnizer.infrastructure.ogarnizerAPI;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ogarnizer.domain.User;
import pl.ogarnizer.infrastructure.ogarnizerAPI.model.UserDTO;

@Component
@AllArgsConstructor
public class UserAPIMapper {

    private final RoleAPIMapper roleMapper;

    public User map(UserDTO userDTO){
        return User.builder()
                .userId(userDTO.getUserId())
                .userName(userDTO.getUserName())
                .password(userDTO.getPassword())
                .roles(userDTO.getRoles().stream().map(roleMapper::map).toList())
                .active(true)
                .build();
    }
}
