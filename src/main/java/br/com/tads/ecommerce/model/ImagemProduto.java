package br.com.tads.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ImagemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String caminho;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
