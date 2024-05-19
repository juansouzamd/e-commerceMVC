package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.Endereco;
import br.com.tads.ecommerce.model.Usuario;
import br.com.tads.ecommerce.repository.EnderecoRepository;
import br.com.tads.ecommerce.repository.UsuarioRepository;
import br.com.tads.ecommerce.service.EnderecoService;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private EnderecoService enderecoService;
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    public UsuarioController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

//    @GetMapping("/usuario")
//    public String mostrarPaginaUsuario(Model model, HttpSession session) {
//        Usuario usuario = (Usuario) session.getAttribute("usuario");
//        if (usuario != null) {
//            Endereco endereco = enderecoService.getEnderecoByUserId(usuario.getId());
//            model.addAttribute("usuario", usuario);
//
//            if(endereco == null){
//                model.addAttribute("endereco", new Endereco());
//            }else {
//                model.addAttribute("endereco", endereco);
//            }
//            return "usuario";
//        } else {
//            return "redirect:/login";
//        }
//    }

    @GetMapping("/usuario")
    public String mostrarPaginaUsuario(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            List<Endereco> enderecos = enderecoService.getEnderecosByUserId(usuario.getId());
            model.addAttribute("usuario", usuario);
            model.addAttribute("endereco", new Endereco()); // Adicione esta linha para fornecer um objeto Endereco vazio

            if (enderecos.isEmpty()) {
                model.addAttribute("enderecos", new ArrayList<Endereco>()); // Se a lista de endereços estiver vazia, adicione uma lista vazia ao modelo
            } else {
                model.addAttribute("enderecos", enderecos); // Caso contrário, adicione a lista de endereços normalmente
            }

            return "usuario";
        } else {
            return "redirect:/login";
        }
    }



    @PostMapping("/usuario/endereco")
    public String criarEndereco(Endereco endereco, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        endereco.setUsuario(usuario);
        enderecoRepository.save(endereco);
        return "redirect:/usuario";
    }

}
