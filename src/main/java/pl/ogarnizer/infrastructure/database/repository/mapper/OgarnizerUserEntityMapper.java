package pl.ogarnizer.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.ogarnizer.domain.User;
import pl.ogarnizer.infrastructure.security.UserEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OgarnizerUserEntityMapper {
    User mapFromEntity(UserEntity entity);

    UserEntity mapToEntity(User user);
}