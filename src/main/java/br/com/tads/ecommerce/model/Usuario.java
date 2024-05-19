package br.com.tads.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 200)
    private String email;

    @Column(nullable = false, length = 100)
    private String senha;

    @Column(length = 14)
    private String cpf;

    @Column(length = 15)
    private String telefone;

    private String dataNascimento;

    private boolean habilitado;

    @OneToMany(mappedBy = "usuario")
    private List<Endereco> enderecos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrinho> itensItemCarrinho;
}
