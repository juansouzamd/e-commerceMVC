package br.com.tads.ecommerce.service;

import br.com.tads.ecommerce.model.Address;
import br.com.tads.ecommerce.model.Orders;
import br.com.tads.ecommerce.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;
    public List<Orders> getOrdersByUserId(Long userId) {
        // Implementação para buscar todos os endereços de um usuário
        return ordersRepository.findByUserId(userId);
    }
}
