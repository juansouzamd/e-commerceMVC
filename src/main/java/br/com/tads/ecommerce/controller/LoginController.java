package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.Usuario;
import br.com.tads.ecommerce.repository.UsuarioRepository;
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
    private UsuarioRepository usuarioRepository;
    @GetMapping("/login")
    public String mostrarPaginaLogin(Model model, @RequestParam(name = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("errorMessage", "Usuário ou senha incorretos.");
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginUsuario(@RequestParam("email") String email,
                               @RequestParam("password") String senha,
                               Model model, HttpSession session) {

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            session.setAttribute("usuario", usuario);
            //usuario.setHabilitado(true);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Credenciais inválidas. Por favor, tente novamente.");
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/logout")
    public String redirecionarLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

