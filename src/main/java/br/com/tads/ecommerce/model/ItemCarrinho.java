package br.com.tads.ecommerce.model;

import br.com.tads.ecommerce.strategy.ShippingContext;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ItemCarrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private int quantidade;

    private double preco;

    public double getTotalPrice() {
        return quantidade * preco;
    }

}
