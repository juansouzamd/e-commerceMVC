package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.Carrinho;
import br.com.tads.ecommerce.model.ItemCarrinho;
import br.com.tads.ecommerce.model.Produto;
import br.com.tads.ecommerce.repository.ItemCarrinhoRepository;
import br.com.tads.ecommerce.service.ProdutoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

    @GetMapping
    public String viewCarrinho(HttpSession session, Model model) {
        Carrinho itemCarrinho = (Carrinho) session.getAttribute("carrinho");
        if (itemCarrinho == null) {
            itemCarrinho = new Carrinho();
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
        Carrinho itemCarrinho = (Carrinho) session.getAttribute("carrinho");
        if (itemCarrinho == null) {
            itemCarrinho = new Carrinho();
            session.setAttribute("carrinho", itemCarrinho);
        }

        Produto produto = produtoService.getProdutoById(produtoId);
        ItemCarrinho item = new ItemCarrinho();
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        item.setPreco(produto.getPreco());
        itemCarrinho.addItem(item);

        itemCarrinhoRepository.save(item);

        return "redirect:/carrinho";
    }

    @PostMapping("/remove")
    public String removeItemFromCarrinho(@RequestParam Long produtoId, HttpSession session) {
        Carrinho itemCarrinho = (Carrinho) session.getAttribute("carrinho");
        if (itemCarrinho != null) {
            itemCarrinho.getItensCarrinho().removeIf(item -> item.getProduto().getId().equals(produtoId));
        }
        return "redirect:/carrinho";
    }
}
