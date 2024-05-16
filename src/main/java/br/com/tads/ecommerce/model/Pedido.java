package br.com.tads.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String imagem;

    @Column(nullable = false, length = 255)
    private String produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Double total;

    private String estimativaEntrega;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(columnDefinition = "TEXT")
    private String endereco;
}

