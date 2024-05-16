package br.com.tads.ecommerce.repository;

import br.com.tads.ecommerce.model.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {
    List<ItemCarrinho> findByUsuarioId(Long usuarioId);
}
