package pl.ogarnizer.infrastructure.ogarnizerAPI;

import org.springframework.stereotype.Component;
import pl.ogarnizer.domain.Role;
import pl.ogarnizer.infrastructure.ogarnizerAPI.model.RoleDTO;

@Component
public class RoleAPIMapper {

    public Role map(RoleDTO roleDTO){
        return Role.builder()
                .roleId(roleDTO.getRoleId())
                .role(roleDTO.getRole())
                .build();
    }
}
