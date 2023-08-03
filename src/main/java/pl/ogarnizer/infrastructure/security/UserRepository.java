package pl.ogarnizer.infrastructure.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

    UserEntity findByUserName(String name);

}
