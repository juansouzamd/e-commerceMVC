package br.com.tads.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false)
    private double preco;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(length = 50)
    private String genero;

    @Column(length = 50)
    private String categoria;

    @Column(length = 50)
    private String marca;

    private String imagemPrincipal;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ImagemProduto> imagens;
}

