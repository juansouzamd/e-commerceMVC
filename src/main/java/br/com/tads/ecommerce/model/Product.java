package br.com.tads.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(length = 50)
    private String gender;

    @Column(length = 50)
    private String category;

    @Column(length = 50)
    private String brand;

    @Column(length = 200)
    private String main_image;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> images;
}

