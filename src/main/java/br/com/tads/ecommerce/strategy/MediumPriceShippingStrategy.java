package br.com.tads.ecommerce.strategy;

public class MediumPriceShippingStrategy implements ShippingStrategy {
    @Override
    public double calculateShipping(double totalPrice) {
        return 10.0;
    }
}
