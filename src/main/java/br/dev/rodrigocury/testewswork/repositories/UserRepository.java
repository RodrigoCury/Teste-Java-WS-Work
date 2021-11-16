package br.dev.rodrigocury.testewswork.repositories;

import br.dev.rodrigocury.testewswork.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
}