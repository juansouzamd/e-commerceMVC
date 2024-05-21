package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.*;
import br.com.tads.ecommerce.repository.CartItemRepository;
import br.com.tads.ecommerce.repository.UserRepository;
import br.com.tads.ecommerce.service.AddressService;
import br.com.tads.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ProductService produtoService;

    @Autowired
    private CartItemRepository itemCarrinhoRepository;
    @Autowired
    private AddressService addressService;

    private UserRepository userRepository;


    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        ShoppingCart cartItem = (ShoppingCart) session.getAttribute("carrinho");
        Users user = (Users) session.getAttribute("usuario");
        if (cartItem == null) {
            cartItem = new ShoppingCart();
            session.setAttribute("carrinho", cartItem);
        }

        List<Address> adresses = addressService.getAddressesByUserId(user.getId());


        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.00", symbols);
        String totalPriceFormatted = df.format(cartItem.getTotalPrice());
        String shippingCostFormatted = df.format(cartItem.calculateShipping());

        // Convertendo as strings formatadas em números
        double totalPrice = Double.parseDouble(totalPriceFormatted);
        double shippingCost = Double.parseDouble(shippingCostFormatted);

        // Calculando o total
        double total = totalPrice + shippingCost;
        String totalValue = df.format(total);

        // Verificando se o frete é grátis
        if (total >= 600) {
            shippingCostFormatted = "Frete Grátis";
        } else {
            // Adicionando o símbolo de moeda ao custo de envio
            shippingCostFormatted = "R$ " + shippingCostFormatted;
        }

        model.addAttribute("shoppingCart", cartItem);
        model.addAttribute("totalPrice", totalPriceFormatted);
        model.addAttribute("shippingCost", shippingCostFormatted);
        model.addAttribute("total", totalValue);


        boolean hasItems = !cartItem.getCartItems().isEmpty();
        boolean hasAddress = !adresses.isEmpty();

        model.addAttribute("hasItems", hasItems);
        model.addAttribute("hasAddress", hasAddress);

        return "shoppingCart";
    }

    @PostMapping("/add")
    public String addItemToCart(@RequestParam int produtoId, @RequestParam int quantidade, HttpSession session) {
        ShoppingCart cartItem = (ShoppingCart) session.getAttribute("carrinho");
        if (cartItem == null) {
            cartItem = new ShoppingCart();
            session.setAttribute("carrinho", cartItem);
        }

        Product product = produtoService.getProductById(produtoId);
        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(quantidade);
        item.setPrice(product.getPrice());
        cartItem.addItem(item);

        itemCarrinhoRepository.save(item);

        return "redirect:/shoppingCart";
    }

    @PostMapping("/remove")
    public String removeItemFromCart(@RequestParam Long produtoId, HttpSession session) {
        ShoppingCart cartItem = (ShoppingCart) session.getAttribute("carrinho");
        if (cartItem != null) {
            cartItem.getCartItems().removeIf(item -> item.getProduct().getId().equals(produtoId));
        }
        return "redirect:/shoppingCart";
    }
}
