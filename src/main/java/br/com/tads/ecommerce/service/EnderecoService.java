package br.com.tads.ecommerce.service;

import br.com.tads.ecommerce.model.Endereco;
import br.com.tads.ecommerce.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco salvarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco getEnderecoByUserId(Long userId) {
        return enderecoRepository.findByUsuario_Id(userId);
    }
}
