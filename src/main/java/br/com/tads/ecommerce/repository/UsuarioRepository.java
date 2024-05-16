package br.com.tads.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.tads.ecommerce.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    Usuario findByEmail(String email);
    Usuario findByNome(String username);
}

