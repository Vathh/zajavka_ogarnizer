package pl.ogarnizer.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.business.dao.UserDAO;
import pl.ogarnizer.domain.User;
import pl.ogarnizer.infrastructure.database.repository.jpa.OgarnizerUserJpaRepository;
import pl.ogarnizer.infrastructure.database.repository.mapper.OgarnizerUserEntityMapper;
import pl.ogarnizer.infrastructure.security.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class OgarnizerUserRepository implements UserDAO {

    private final OgarnizerUserJpaRepository ogarnizerUserJpaRepository;
    private final OgarnizerUserEntityMapper ogarnizerUserEntityMapper;

    @Override
    public Optional<User> findByUserName(String name) {
        return ogarnizerUserJpaRepository.findByUserName(name).map(ogarnizerUserEntityMapper::mapFromEntity);
    }

    @Override
    public List<User> findUsers() {
        return ogarnizerUserJpaRepository.findAll().stream().map(ogarnizerUserEntityMapper::mapFromEntity).toList();
    }

    @Override
    public void addUser(User user) {
        UserEntity userEntity = ogarnizerUserEntityMapper.mapToEntity(user);

        ogarnizerUserJpaRepository.saveAndFlush(userEntity);
    }

    @Override
    public Optional<User> findByUserId(Integer userId) {
        return ogarnizerUserJpaRepository.findById(userId).map(ogarnizerUserEntityMapper::mapFromEntity);
    }

    @Override
    public void deleteUser(Integer userId) {
        ogarnizerUserJpaRepository.deleteById(userId);
    }
}
