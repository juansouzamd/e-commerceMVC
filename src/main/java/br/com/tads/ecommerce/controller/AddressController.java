package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.Address;
import br.com.tads.ecommerce.model.Users;
import br.com.tads.ecommerce.service.AddressService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AddressController {
    @Autowired
    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/atualizar-endereco/{id}")
    public String mostrarPaginaEndereco(Model model, @PathVariable Long id){
        Address endereco = addressService.getAddressById(id);
        model.addAttribute("endereco", endereco);
        return "endereco";
    }

    @PostMapping("/atualizar-endereco/{id}")
    public String atualizarEndereco(@ModelAttribute("enderecoAtualizado") Address enderecoAtualizado,
                                    HttpServletRequest request, @PathVariable Long id) {

        Users usuario = (Users) request.getSession().getAttribute("usuario");
        Address endereco = addressService.getAddressById(id);


            // Atualizar os campos do endereço existente com os dados do endereço atualizado
            endereco.setStreet(enderecoAtualizado.getStreet());
            endereco.setCep(enderecoAtualizado.getCep());
            endereco.setNeighborhood(enderecoAtualizado.getNeighborhood());
            endereco.setNumber(enderecoAtualizado.getNumber());

            addressService.createAddress(endereco);


        return "redirect:/usuario";
    }



    @PostMapping("/excluir-endereco")
    public String excluirEndereco(@RequestParam Long id, @RequestParam Long userId, HttpSession session) {
        addressService.deleteAddressById(id);
        // Atualize a lista de endereços do usuário na sessão após a exclusão
        Users usuario = (Users) session.getAttribute("usuario");
        if (usuario != null && usuario.getId().equals(userId)) {
            List<Address> enderecos = addressService.getAddressesByUserId(userId);
            session.setAttribute("enderecos", enderecos);
        }
        return "redirect:/usuario";
    }
}
