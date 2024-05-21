package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.Users;
import br.com.tads.ecommerce.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserRepository usuarioRepository;
    @GetMapping("/login")
    public String showLoginPage(Model model, @RequestParam(name = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("errorMessage", "Usuário ou senha incorretos.");
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model, HttpSession session) {

        Users user = usuarioRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("usuario", user);
            //usuario.setHabilitado(true);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Credenciais inválidas. Por favor, tente novamente.");
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/logout")
    public String redirectLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

