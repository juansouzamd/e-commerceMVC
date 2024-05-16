package br.com.tads.ecommerce.service;

import br.com.tads.ecommerce.model.ImagemProduto;
import br.com.tads.ecommerce.repository.ImagemProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImagemProdutoService {

    @Autowired
    private ImagemProdutoRepository imagemProdutoRepository;

    public List<String> findCaminhosByProdutoId(Long produtoId) {
        List<ImagemProduto> imagens = imagemProdutoRepository.findByProdutoId(produtoId);
        List<String> caminhos = new ArrayList<>();
        for (ImagemProduto imagem : imagens) {
            caminhos.add(imagem.getCaminho());
        }
        return caminhos;
    }
}

