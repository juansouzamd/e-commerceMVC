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

    @GetMapping("/update-address/{id}")
    public String showPageAddress(Model model, @PathVariable Long id){
        Address address = addressService.getAddressById(id);
        model.addAttribute("address", address);
        return "updateAddress";
    }

    @PostMapping("/update-address/{id}")
    public String updateAddress(@ModelAttribute("updateAddress") Address updateAddress,
                                    HttpServletRequest request, @PathVariable Long id) {

        Users user = (Users) request.getSession().getAttribute("usuario");
        Address address = addressService.getAddressById(id);

            address.setStreet(updateAddress.getStreet());
            address.setCep(updateAddress.getCep());
            address.setNeighborhood(updateAddress.getNeighborhood());
            address.setNumber(updateAddress.getNumber());

            addressService.createAddress(address);

        return "redirect:/user";
    }



    @PostMapping("/delete-address")
    public String deleteAddress(@RequestParam("id") Long id, @RequestParam("userId") Long userId, HttpSession session) {
        addressService.deleteAddressById(id);

        Users user = (Users) session.getAttribute("usuario");
        if (user != null && user.getId().equals(userId)) {
            List<Address> addresses = addressService.getAddressesByUserId(userId);
            session.setAttribute("addresses", addresses);
        }
        return "redirect:/user";
    }
}
