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
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findById(user.getId());
        return optionalCart.orElseGet(ShoppingCart::new);
    }

    public void addItemToShoppingCart(CartItem item, Users user) {
        ShoppingCart cart = getShoppingCartByUser(user);
        item.setUser(user);
        cart.addItem(item);
        cartItemRepository.save(item);
        shoppingCartRepository.save(cart);
    }

    public double getTotalPrice(ShoppingCart cart) {
        return cart.getTotalPrice();
    }

    public double getShippingCost(ShoppingCart cart) {
        return cart.calculateShipping();
    }

    public void clearCart(Users user) {
        ShoppingCart cart = getShoppingCartByUser(user);
        cart.getCartItems().clear();
        shoppingCartRepository.save(cart);
    }
}

