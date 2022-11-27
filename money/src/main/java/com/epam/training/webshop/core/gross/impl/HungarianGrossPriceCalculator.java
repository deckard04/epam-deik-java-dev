package com.epam.training.webshop.core.gross.impl;

import com.epam.training.webshop.core.cart.ShoppingCartService;
import com.epam.training.webshop.core.gross.GrossPriceCalculator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HungarianGrossPriceCalculator extends GrossPriceCalculatorDecorator {

    private final double taxRate;

    public HungarianGrossPriceCalculator(final GrossPriceCalculator grossPriceCalculator, @Value("${gross.tax-rate.hun:2}") final Double taxRate) {
        super(grossPriceCalculator);
        this.taxRate = taxRate;
    }

    @Override
    public double getAggregatedGrossPrice(final ShoppingCartService shoppingCartService) {
        return super.getAggregatedGrossPrice(shoppingCartService) * taxRate;
    }
}
