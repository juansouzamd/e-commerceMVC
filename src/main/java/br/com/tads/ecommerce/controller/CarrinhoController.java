package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.ItemCarrinho;
import br.com.tads.ecommerce.repository.ItemCarrinhoRepository;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class CarrinhoController {

    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

    @GetMapping("/carrinho")
    public String mostrarPaginaCarrinho(Model model, Principal principal) {
        // Simulando a obtenção do ID do usuário logado
        Long usuarioId = 1L; // Isso deve ser obtido a partir do usuário autenticado

        List<ItemCarrinho> itensCarrinho = itemCarrinhoRepository.findByUsuarioId(usuarioId);
        model.addAttribute("itensCarrinho", itensCarrinho);

        return "carrinho";
    }

    @GetMapping("/checkout")
    public String mostrarPaginaCheckout(){
        return "checkout";
    }

    @GetMapping("/obrigado")
    public String mostrarPaginaObrigado(){
        return "obrigado";
    }
}
