package br.com.tads.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String cep;

    private String neighborhood;

    @Column(nullable = false)
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
