package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.Address;
import br.com.tads.ecommerce.model.Orders;
import br.com.tads.ecommerce.model.Users;
import br.com.tads.ecommerce.service.AddressService;
import br.com.tads.ecommerce.service.OrdersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private AddressService addressService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    public UserController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public String showPageUser(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("usuario");
        if (user != null) {
            List<Address> adresses = addressService.getAddressesByUserId(user.getId());
            List<Orders> orders = ordersService.getOrdersByUserId(user.getId());
            model.addAttribute("user", user);
            model.addAttribute("address", new Address());

            model.addAttribute("adresses", adresses.isEmpty() ? new ArrayList<>() : adresses);
            model.addAttribute("orders", orders.isEmpty() ? new ArrayList<>() : orders);
            }

            return "user";
    }

    @PostMapping("/address")
    public String criarEndereco(Address endereco, HttpSession session) {
        Users user = (Users) session.getAttribute("usuario");
        endereco.setUser(user);
        addressService.createAddress(endereco);
        return "redirect:/user";
    }

}
