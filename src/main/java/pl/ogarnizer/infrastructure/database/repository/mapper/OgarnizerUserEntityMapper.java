package pl.ogarnizer.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.ogarnizer.domain.User;
import pl.ogarnizer.infrastructure.security.RoleEntity;
import pl.ogarnizer.infrastructure.security.UserEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OgarnizerUserEntityMapper {
    User mapFromEntity(UserEntity entity);

//    {
//        return User.builder()
//                .userId(entity.getUserId())
//                .userName(entity.getUserName())
//                .login(entity.getLogin())
//                .password(entity.getPassword())
//                .roles(entity.getRoles().stream().map(role -> OgarnizerRoleEntityMapper))
//                .build();
//    };

    UserEntity mapToEntity(User user);

//    {
//        return UserEntity.builder()
//                .userName(user.getUserName())
//                .login(user.getLogin())
//                .password(user.getPassword())
//                .roles(user.getRoles())
//                .build();
//    }
}