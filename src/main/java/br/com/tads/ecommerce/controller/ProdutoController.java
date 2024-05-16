package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.ImagemProduto;
import br.com.tads.ecommerce.model.Produto;
import br.com.tads.ecommerce.service.ProdutoService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@Controller
public class ProdutoController {
    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/")
    public String getProdutos(Model model) {
        List<Produto> produtos = produtoService.getAllProdutos();
        int tamanhoLista = produtos.size();
        int metade = tamanhoLista / 2;
        List<Produto> primeiraMetadeProdutos = produtos.subList(0, metade);
        List<Produto> segundaMetadeProdutos = produtos.subList(metade, tamanhoLista);
        model.addAttribute("primeiraMetadeProdutos", primeiraMetadeProdutos);
        model.addAttribute("segundaMetadeProdutos", segundaMetadeProdutos);
        return "index";
    }

    @GetMapping("/produto/{id}")
    public String getProduto(@PathVariable int id, Model model) {
        Produto produto = produtoService.getProdutoById(id);
        List<ImagemProduto> imagens = produtoService.getImagensByProdutoId(produto.getId());
        model.addAttribute("produto", produto);
        model.addAttribute("imagens", imagens);
        model.addAttribute("id", id);

        List<Produto> todosOsProdutos = produtoService.getAllProdutos();
        todosOsProdutos.removeIf(x -> x.getId().equals(id));
        Collections.shuffle(todosOsProdutos);
        List<Produto> produtosAleatorios = todosOsProdutos.subList(0, 4);
        model.addAttribute("produtosAleatorios", produtosAleatorios);

        return "produto";
    }

}
