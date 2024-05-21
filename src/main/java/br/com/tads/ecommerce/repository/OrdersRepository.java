package br.com.tads.ecommerce.repository;

import br.com.tads.ecommerce.model.Address;
import br.com.tads.ecommerce.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByUserId(Long userId);
}
