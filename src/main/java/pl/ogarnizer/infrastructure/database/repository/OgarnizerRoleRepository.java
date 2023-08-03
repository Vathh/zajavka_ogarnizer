package pl.ogarnizer.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.business.dao.RoleDAO;
import pl.ogarnizer.domain.Role;
import pl.ogarnizer.infrastructure.database.repository.jpa.OgarnizerRoleJpaRepository;
import pl.ogarnizer.infrastructure.database.repository.mapper.OgarnizerRoleEntityMapper;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class OgarnizerRoleRepository implements RoleDAO {

    private final OgarnizerRoleJpaRepository ogarnizerRoleJpaRepository;
    private final OgarnizerRoleEntityMapper ogarnizerRoleEntityMapper;

    @Override
    public List<Role> findRoles() {
        return ogarnizerRoleJpaRepository.findAll().stream().map(ogarnizerRoleEntityMapper::mapFromEntity).toList();
    }

    @Override
    public Optional<Role> findRole(String role) {
        return ogarnizerRoleJpaRepository.findByRole(role).map(ogarnizerRoleEntityMapper::mapFromEntity);
    }
}
