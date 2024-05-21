package br.com.tads.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.tads.ecommerce.model.Users;
import br.com.tads.ecommerce.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void createUser(Users user) {
        userRepository.save(user);
    }

    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean verifyCredentials(String email, String password) {
        Users user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }


}

