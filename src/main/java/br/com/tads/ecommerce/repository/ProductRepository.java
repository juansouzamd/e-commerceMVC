package br.com.tads.ecommerce.repository;

import br.com.tads.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findById(Integer id);
    List<Product> findAll();
    void delete(Product product);

}

