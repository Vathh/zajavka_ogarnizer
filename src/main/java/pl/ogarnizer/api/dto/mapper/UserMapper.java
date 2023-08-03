package pl.ogarnizer.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.ogarnizer.api.dto.UserDTO;
import pl.ogarnizer.domain.Role;
import pl.ogarnizer.domain.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    default UserDTO map(User user){
        return UserDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .login(user.getLogin())
                .roles(user.getRoles().stream().map(Role::getRole).toList())
                .build();
    }
}
