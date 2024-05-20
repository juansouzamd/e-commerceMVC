package br.com.tads.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String image;

    @Column(nullable = false, length = 255)
    private String product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double total;

    private String deliveryEstimate;//#tirar

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(columnDefinition = "TEXT")
    private String address; //#tirar
}

