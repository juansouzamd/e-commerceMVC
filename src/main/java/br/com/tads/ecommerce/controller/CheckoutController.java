package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.*;
import br.com.tads.ecommerce.repository.OrdersRepository;
import br.com.tads.ecommerce.service.AddressService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private AddressService addressService;

    @GetMapping
    public String checkout(HttpSession session, Model model) {
        ShoppingCart cartItem = (ShoppingCart) session.getAttribute("carrinho");
        Users user = (Users) session.getAttribute("usuario");
        List<Address> adresses = addressService.getAddressesByUserId(user.getId());


        model.addAttribute("cartItem", cartItem);
        model.addAttribute("addresses", adresses);

        return "checkout";
    }

    @PostMapping("/order")
    public String placeOrder(HttpSession session, Model model, String opcaoEndereco) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("carrinho");
        Users user = (Users) session.getAttribute("usuario");

        List<Orders> orders = new ArrayList<>();

        for (CartItem cartItem : shoppingCart.getCartItems()) {
            Orders order = new Orders();
            order.setUser(user);
            order.setName(cartItem.getProduct().getName());
            order.setImage(cartItem.getProduct().getMain_image());
            order.setPrice(cartItem.getProduct().getPrice());
            order.setValueTotal(cartItem.getTotalPrice());
            order.setAddress(opcaoEndereco);
            order.setQuantity(cartItem.getQuantity());
            ordersRepository.save(order);
            orders.add(order);
        }


        session.removeAttribute("carrinho");
        model.addAttribute("orders", orders);

        return "thanks";
    }
}