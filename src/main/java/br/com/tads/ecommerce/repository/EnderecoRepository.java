package br.com.tads.ecommerce.repository;

import br.com.tads.ecommerce.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Endereco findByUsuario_Id(Long userId);
}

