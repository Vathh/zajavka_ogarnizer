package pl.ogarnizer.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.dto.RoleDTO;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.domain.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO map(final Role role);

    Role map(final RoleDTO roleDTO);
}
