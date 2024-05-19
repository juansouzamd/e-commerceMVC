package br.com.tads.ecommerce.strategy;

public class HighPriceShippingStrategy implements ShippingStrategy {
    @Override
    public double calculateShipping(double totalPrice) {
        return 0.0;
    }
}
