package br.com.tads.ecommerce.model;

import br.com.tads.ecommerce.strategy.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrinho> itensCarrinho = new ArrayList<>();

    @Transient
    private ShippingContext shippingContext = new ShippingContext();

    public void addItem(ItemCarrinho item) {
        itensCarrinho.add(item);
    }

    public double getTotalPrice() {
        return itensCarrinho.stream().mapToDouble(ItemCarrinho::getTotalPrice).sum();
    }

    public double calculateShipping() {
        double totalPrice = getTotalPrice();

        if (totalPrice < 300) {
            shippingContext.setStrategy(new LowPriceShippingStrategy());
        } else if (totalPrice < 600) {
            shippingContext.setStrategy(new MediumPriceShippingStrategy());
        } else {
            shippingContext.setStrategy(new HighPriceShippingStrategy());
        }

        return shippingContext.executeStrategy(totalPrice);
    }
}
