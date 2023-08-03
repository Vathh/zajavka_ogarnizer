package pl.ogarnizer.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.ogarnizer.domain.Role;
import pl.ogarnizer.infrastructure.security.RoleEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OgarnizerRoleEntityMapper {

    Role mapFromEntity(RoleEntity entity);

    RoleEntity mapToEntity(Role role);
}
