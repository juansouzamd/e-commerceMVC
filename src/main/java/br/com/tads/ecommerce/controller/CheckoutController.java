package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.ShoppingCart;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @GetMapping
    public String checkout(HttpSession session, Model model) {
        ShoppingCart cartItem = (ShoppingCart) session.getAttribute("carrinho");

        return "checkout";
    }
}
