package br.com.tads.ecommerce.service;

import br.com.tads.ecommerce.model.Endereco;
import br.com.tads.ecommerce.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco salvarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco buscarEnderecoPorId(Long id) {
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);
        return optionalEndereco.orElse(null);
    }

    public List<Endereco> getEnderecosByUserId(Long userId) {
        // Implementação para buscar todos os endereços de um usuário
        return enderecoRepository.findByUsuario_Id(userId);
    }


}
