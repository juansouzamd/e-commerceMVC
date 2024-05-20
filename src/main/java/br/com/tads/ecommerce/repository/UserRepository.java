package br.com.tads.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.tads.ecommerce.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);
    Users findByEmail(String email);
    Users findByName(String username);
}

