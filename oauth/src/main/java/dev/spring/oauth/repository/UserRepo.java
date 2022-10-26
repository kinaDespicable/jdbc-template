package dev.spring.oauth.repository;

import dev.spring.oauth.controller.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
