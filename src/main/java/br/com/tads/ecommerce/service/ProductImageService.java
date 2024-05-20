package br.com.tads.ecommerce.service;

import br.com.tads.ecommerce.model.ProductImage;
import br.com.tads.ecommerce.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    public List<String> GetPathsByProductId(Long productId) {
        List<ProductImage> images = productImageRepository.findByProductId(productId);
        List<String> paths = new ArrayList<>();
        for (ProductImage image : images) {
            paths.add(image.getPath());
        }
        return paths;
    }
}

