package br.com.tads.ecommerce.service;

import br.com.tads.ecommerce.model.ImagemProduto;
import br.com.tads.ecommerce.model.Produto;
import br.com.tads.ecommerce.repository.ImagemProdutoRepository;
import br.com.tads.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private  ImagemProdutoRepository imagemProdutoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, ImagemProdutoRepository imagemProdutoRepository) {
        this.produtoRepository = produtoRepository;
        this.imagemProdutoRepository = imagemProdutoRepository;
    }

    public Produto getProdutoById(int id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public List<ImagemProduto> getImagensByProdutoId(Long id) {
        return imagemProdutoRepository.findByProdutoId(id);
    }
}
