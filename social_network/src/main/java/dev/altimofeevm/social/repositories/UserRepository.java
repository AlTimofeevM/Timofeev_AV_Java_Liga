package dev.altimofeevm.social.repositories;

import dev.altimofeevm.social.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>,
                        JpaSpecificationExecutor<User> {
    User findByLogin(String login);
}
