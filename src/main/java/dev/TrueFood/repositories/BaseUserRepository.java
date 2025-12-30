package dev.TrueFood.repositories;

import dev.TrueFood.entity.users.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseUserRepository extends JpaRepository<BaseUser, Integer> {
    Optional<BaseUser> findByEmail(String email);
}
