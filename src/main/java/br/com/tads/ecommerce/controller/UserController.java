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

    @GetMapping("/user")
    public String showPageUser(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("usuario");
        if (user != null) {
            List<Address> Adresses = addressService.getAddressesByUserId(user.getId());
            model.addAttribute("user", user);
            model.addAttribute("address", new Address()); // Adicione esta linha para fornecer um objeto Endereco vazio

            if (Adresses.isEmpty()) {
                model.addAttribute("adresses", new ArrayList<Address>()); // Se a lista de endereços estiver vazia,
                // adicione uma lista vazia ao modelo
            } else {
                model.addAttribute("adresses", Adresses); // Caso contrário, adicione a lista de endereços normalmente
            }

            return "user";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/user/address")
    public String criarEndereco(Address endereco, HttpSession session) {
        Users user = (Users) session.getAttribute("usuario");
        endereco.setUser(user);
        addressService.createAddress(endereco);
        return "redirect:/user";
    }

}
