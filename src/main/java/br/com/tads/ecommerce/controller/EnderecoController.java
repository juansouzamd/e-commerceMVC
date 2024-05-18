package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.Endereco;
import br.com.tads.ecommerce.model.Usuario;
import br.com.tads.ecommerce.repository.EnderecoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("/endereco")
    public String mostrarPaginaEndereco(Model model){
        return "endereco";
    }



    @PostMapping("/excluir-endereco/{id}")
    public String excluirEndereco(@PathVariable Long id, HttpServletRequest request) {
        // Recuperar o objeto Usuario da sessão HTTP
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        Endereco endereco = enderecoRepository.findById(id).orElse(null);
        if (endereco != null && endereco.getUsuario().equals(usuario)) {
            enderecoRepository.delete(endereco);
        }
        return "redirect:/usuario";
    }
}
