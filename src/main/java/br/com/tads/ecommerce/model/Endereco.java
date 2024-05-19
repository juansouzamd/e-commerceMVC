package br.com.tads.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private String cep;

    private String bairro;

    @Column(nullable = false)
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
