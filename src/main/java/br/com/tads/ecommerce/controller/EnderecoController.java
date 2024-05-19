package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.Endereco;
import br.com.tads.ecommerce.model.Usuario;
import br.com.tads.ecommerce.repository.EnderecoRepository;
import br.com.tads.ecommerce.service.EnderecoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;
    private EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/atualizar-endereco/{id}")
    public String mostrarPaginaEndereco(Model model, @PathVariable Long id){
        Endereco endereco = enderecoService.buscarEnderecoPorId(id);
        model.addAttribute("endereco", endereco);
        return "endereco";
    }

    @PostMapping("/atualizar-endereco/{id}")
    public String atualizarEndereco(@ModelAttribute("enderecoAtualizado") Endereco enderecoAtualizado,
                                    HttpServletRequest request, @PathVariable Long id) {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);
        Endereco endereco = optionalEndereco.get();

            // Atualizar os campos do endereço existente com os dados do endereço atualizado
            endereco.setRua(enderecoAtualizado.getRua());
            endereco.setCep(enderecoAtualizado.getCep());
            endereco.setBairro(enderecoAtualizado.getBairro());
            endereco.setNumero(enderecoAtualizado.getNumero());

            enderecoRepository.save(endereco);


        return "redirect:/usuario";
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
