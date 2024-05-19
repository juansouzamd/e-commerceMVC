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
import org.springframework.web.bind.annotation.*;

import java.util.List;
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



    @PostMapping("/excluir-endereco")
    public String excluirEndereco(@RequestParam Long id, @RequestParam Long userId, HttpSession session) {
        enderecoService.deleteEnderecoById(id);
        // Atualize a lista de endereços do usuário na sessão após a exclusão
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null && usuario.getId().equals(userId)) {
            List<Endereco> enderecos = enderecoService.getEnderecosByUserId(userId);
            session.setAttribute("enderecos", enderecos);
        }
        return "redirect:/usuario";
    }
}
