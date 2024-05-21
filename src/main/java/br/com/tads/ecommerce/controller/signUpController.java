package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.Users;
import br.com.tads.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signUp")
public class signUpController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String registerUser(Users user, Model model) {
        if (userRepository.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "O e-mail fornecido já está em uso.");
            return "login";
        }
        userRepository.save(user);
        model.addAttribute("message", "Usuário cadastrado com sucesso!");
        return "redirect:/login";
    }

    @GetMapping
    public String showPageRegistration(Model model) {
        model.addAttribute("users", new Users());
        return "signUp";
    }

}
