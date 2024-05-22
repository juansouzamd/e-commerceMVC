/*
    Nome do Projeto: E-commerce
    Data de Criação: 15/02/2024
    Versão: 5
    Data da Última Modificação: 22/05/2024
    Versão do Java: 17
    Versão do Spring Boot: 3.2.4
    Banco de dados: H2
    Equipe de Desenvolvimento:
        - Juan Souza Santos
        - Maria Helena dos Santos
        - Pedro Ferreira Lima
        - Thiago Shigueto Hossaka

    Descrição: Este programa é uma plataforma de e-commerce destinada à venda de produtos variados.
        A aplicação permite aos usuários navegar por uma lista de produtos, adicionar itens ao carrinho
        de compras, calcular o custo total incluindo frete e finalizar a compra.

    Funcionalidades incluem cadastro e login de usuários, exibição de produtos, cálculo de preços,
        gerenciamento do carrinho de compras, checkout e armazenamento dos resultados em um banco de dados.
*/

package br.com.tads.ecommerce.service;

import br.com.tads.ecommerce.model.ShoppingCart;
import br.com.tads.ecommerce.model.CartItem;
import br.com.tads.ecommerce.model.Users;
import br.com.tads.ecommerce.repository.ShoppingCartRepository;
import br.com.tads.ecommerce.repository.CartItemRepository;
import br.com.tads.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository usuarioRepository;

    public ShoppingCart getShoppingCartByUser(Users user) {
        // Obtém o carrinho de compras do usuário pelo ID do usuário
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findById(user.getId());

        // Retorna o carrinho de compras, ou cria um novo se não existir
        return optionalCart.orElseGet(ShoppingCart::new);
    }

    public void addItemToShoppingCart(CartItem item, Users user) {
        // Obtém o carrinho de compras do usuário
        ShoppingCart cart = getShoppingCartByUser(user);

        // Define o usuário para o item e adiciona-o ao carrinho
        item.setUser(user);
        cart.addItem(item);

        // Salva o item do carrinho e o carrinho de compras no banco de dados
        cartItemRepository.save(item);
        shoppingCartRepository.save(cart);
    }

    public double getTotalPrice(ShoppingCart cart) {
        // Obtém o preço total do carrinho de compras
        return cart.getTotalPrice();
    }

    public double getShippingCost(ShoppingCart cart) {
        // Obtém o custo de envio do carrinho de compras
        return cart.calculateShipping();
    }

    public void clearCart(Users user) {
        // Limpa o carrinho de compras do usuário
        ShoppingCart cart = getShoppingCartByUser(user);
        cart.getCartItems().clear();
        shoppingCartRepository.save(cart);
    }
}

