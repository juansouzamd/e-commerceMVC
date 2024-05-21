package br.com.tads.ecommerce.controller;

import br.com.tads.ecommerce.model.ProductImage;
import br.com.tads.ecommerce.model.Product;
import br.com.tads.ecommerce.service.ProductService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {
    private final ProductService produtoService;

    @Autowired
    public ProductController(ProductService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public String getProdutos(Model model) {
        List<Product> product = produtoService.getAllProducts();
        int sizeList = product.size();
        int half = sizeList / 2;
        List<Product> firstHalfProducts = product.subList(0, half);
        List<Product> secondHalfProducts = product.subList(half, sizeList);
        model.addAttribute("firstHalfProducts", firstHalfProducts);
        model.addAttribute("secondHalfProducts", secondHalfProducts);
        return "index";
    }

    @GetMapping("product/{id}")
    public String getProduto(@PathVariable int id, Model model) {
        Product product = produtoService.getProductById(id);
        List<ProductImage> images = produtoService.getImagesByProductId(product.getId());
        model.addAttribute("product", product);
        model.addAttribute("images", images);
        model.addAttribute("id", id);

        List<Product> allProducts = produtoService.getAllProducts();
        allProducts.removeIf(x -> x.getId().equals(id));
        Collections.shuffle(allProducts);
        List<Product> randomProducts = allProducts.subList(0, 4);
        model.addAttribute("randomProducts", randomProducts);

        return "product";
    }

}
