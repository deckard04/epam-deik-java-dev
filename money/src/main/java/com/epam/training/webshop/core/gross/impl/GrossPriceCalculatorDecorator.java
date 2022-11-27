package com.epam.training.webshop.core.gross.impl;

import com.epam.training.webshop.core.cart.ShoppingCartService;
import com.epam.training.webshop.core.gross.GrossPriceCalculator;

public class GrossPriceCalculatorDecorator implements GrossPriceCalculator {

    private final GrossPriceCalculator grossPriceCalculator;

    public GrossPriceCalculatorDecorator(final GrossPriceCalculator grossPriceCalculator) {
        this.grossPriceCalculator = grossPriceCalculator;
    }

    @Override
    public double getAggregatedGrossPrice(final ShoppingCartService shoppingCartService) {
        return grossPriceCalculator.getAggregatedGrossPrice(shoppingCartService);
    }
}
