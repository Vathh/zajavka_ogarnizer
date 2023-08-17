package pl.ogarnizer.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ogarnizer.business.dao.RoleDAO;
import pl.ogarnizer.domain.Role;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RoleService {

    private final RoleDAO roleDAO;

    @Transactional
    public List<Role> findRoles(){
        List<Role> roles = roleDAO.findRoles();
        log.info("Roles: [{}]", roles.size());
        return roles;
    }
}
