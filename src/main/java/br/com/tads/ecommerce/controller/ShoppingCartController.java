package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.ShoppingCart;
import br.com.tads.ecommerce.model.CartItem;
import br.com.tads.ecommerce.model.Product;
import br.com.tads.ecommerce.repository.CartItemRepository;
import br.com.tads.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ProductService produtoService;

    @Autowired
    private CartItemRepository itemCarrinhoRepository;

    @GetMapping
    public String viewCarrinho(HttpSession session, Model model) {
        ShoppingCart itemCarrinho = (ShoppingCart) session.getAttribute("carrinho");
        if (itemCarrinho == null) {
            itemCarrinho = new ShoppingCart();
            session.setAttribute("carrinho", itemCarrinho);
        }

        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.00", symbols);
        String totalPriceFormatted = df.format(itemCarrinho.getTotalPrice());
        String shippingCostFormatted = df.format(itemCarrinho.calculateShipping());

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

        model.addAttribute("carrinho", itemCarrinho);
        model.addAttribute("totalPrice", totalPriceFormatted);
        model.addAttribute("shippingCost", shippingCostFormatted);
        model.addAttribute("total", totalValue);

        return "carrinho";
    }

    @PostMapping("/add")
    public String addItemToCarrinho(@RequestParam int produtoId, @RequestParam int quantidade, HttpSession session) {
        ShoppingCart itemCarrinho = (ShoppingCart) session.getAttribute("carrinho");
        if (itemCarrinho == null) {
            itemCarrinho = new ShoppingCart();
            session.setAttribute("carrinho", itemCarrinho);
        }

        Product produto = produtoService.getProductById(produtoId);
        CartItem item = new CartItem();
        item.setProduct(produto);
        item.setQuantity(quantidade);
        item.setPrice(produto.getPrice());
        itemCarrinho.addItem(item);

        itemCarrinhoRepository.save(item);

        return "redirect:/carrinho";
    }

    @PostMapping("/remove")
    public String removeItemFromCarrinho(@RequestParam Long produtoId, HttpSession session) {
        ShoppingCart itemCarrinho = (ShoppingCart) session.getAttribute("carrinho");
        if (itemCarrinho != null) {
            itemCarrinho.getCartItems().removeIf(item -> item.getProduct().getId().equals(produtoId));
        }
        return "redirect:/carrinho";
    }
}
