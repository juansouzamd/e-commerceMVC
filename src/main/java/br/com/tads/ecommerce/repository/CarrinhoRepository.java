package br.com.tads.ecommerce.repository;

import br.com.tads.ecommerce.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    // Aqui você pode definir métodos de consulta personalizados, se necessário
}
