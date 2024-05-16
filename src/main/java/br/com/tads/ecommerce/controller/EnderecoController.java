package br.com.tads.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnderecoController {

    @GetMapping("/endereco")
    public String mostrarPaginaEndereco(Model model){
        return "endereco";
    }
}
