package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.Address;
import br.com.tads.ecommerce.model.Users;
import br.com.tads.ecommerce.service.AddressService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private AddressService addressService;

    @Autowired
    public UserController(AddressService addressService) {
        this.addressService = addressService;
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

    @GetMapping("/user")
    public String mostrarPaginaUsuario(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("usuario");
        if (user != null) {
            List<Address> enderecos = addressService.getAddressesByUserId(user.getId());
            model.addAttribute("usuario", user);
            model.addAttribute("endereco", new Address()); // Adicione esta linha para fornecer um objeto Endereco vazio

            if (enderecos.isEmpty()) {
                model.addAttribute("enderecos", new ArrayList<Address>()); // Se a lista de endereços estiver vazia, adicione uma lista vazia ao modelo
            } else {
                model.addAttribute("enderecos", enderecos); // Caso contrário, adicione a lista de endereços normalmente
            }

            return "user";
        } else {
            return "redirect:/login";
        }
    }



    @PostMapping("/usuario/endereco") // nao é usado
    public String criarEndereco(Address endereco, HttpSession session) {
        Users usuario = (Users) session.getAttribute("usuario");
        endereco.setUser(usuario);
        addressService.createAddress(endereco);
        return "redirect:/user";
    }

}
