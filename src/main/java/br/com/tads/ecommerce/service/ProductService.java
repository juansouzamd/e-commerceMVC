package br.com.tads.ecommerce.service;

import br.com.tads.ecommerce.model.ProductImage;
import br.com.tads.ecommerce.model.Product;
import br.com.tads.ecommerce.repository.ProductImageRepository;
import br.com.tads.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private ProductImageRepository productImageRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductImage> getImagesByProductId(Long id) {
        return productImageRepository.findByProductId(id);
    }
}
