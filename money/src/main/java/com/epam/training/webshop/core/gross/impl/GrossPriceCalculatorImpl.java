package com.epam.training.webshop.core.gross.impl;

import com.epam.training.webshop.core.cart.ShoppingCartService;
import com.epam.training.webshop.core.gross.GrossPriceCalculator;
import org.springframework.stereotype.Component;

@Component
public class GrossPriceCalculatorImpl implements GrossPriceCalculator {
    @Override
    public double getAggregatedGrossPrice(final ShoppingCartService shoppingCartService) {
        return shoppingCartService.getTotalNetPrice();
    }
}
