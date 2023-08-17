package pl.ogarnizer.business.dao;

import pl.ogarnizer.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDAO {
    List<Role> findRoles();
    Optional<Role> findRole(String role);
}
