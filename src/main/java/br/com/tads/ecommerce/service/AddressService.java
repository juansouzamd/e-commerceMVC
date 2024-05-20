package br.com.tads.ecommerce.service;

import br.com.tads.ecommerce.model.Address;
import br.com.tads.ecommerce.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address getAddressById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }

    public List<Address> getAddressesByUserId(Long userId) {
        // Implementação para buscar todos os endereços de um usuário
        return addressRepository.findByUserId(userId);
    }


    public void deleteAddressById(Long id) {
        addressRepository.deleteById(id);
    }
}
