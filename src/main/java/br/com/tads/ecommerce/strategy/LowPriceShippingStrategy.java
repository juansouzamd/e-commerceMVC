package br.com.tads.ecommerce.strategy;

public class LowPriceShippingStrategy implements ShippingStrategy {
    @Override
    public double calculateShipping(double totalPrice) {
        return 20.0;
    }
}
