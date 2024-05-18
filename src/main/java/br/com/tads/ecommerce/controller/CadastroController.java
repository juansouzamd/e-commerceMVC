package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.Usuario;
import br.com.tads.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CadastroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/cadastro")
    public String cadastrarUsuario(Usuario usuario, Model model) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            model.addAttribute("error", "O e-mail fornecido já está em uso.");
            return "login";
        }
        usuarioRepository.save(usuario);
        model.addAttribute("message", "Usuário cadastrado com sucesso!");

        return "redirect:/login";
    }

    @GetMapping("/cadastro")
    public String mostrarPaginaCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

}
