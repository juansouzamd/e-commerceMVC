package br.com.tads.ecommerce.strategy;

public class ShippingContext {
    private ShippingStrategy strategy;

    public void setStrategy(ShippingStrategy strategy) {
        this.strategy = strategy;
    }

    public double executeStrategy(double totalPrice) {
        return strategy.calculateShipping(totalPrice);
    }
}
