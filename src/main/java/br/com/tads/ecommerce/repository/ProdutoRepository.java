package br.com.tads.ecommerce.repository;

import br.com.tads.ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Optional<Produto> findById(Integer id);
    List<Produto> findAll();
    void delete(Produto produto);

}

