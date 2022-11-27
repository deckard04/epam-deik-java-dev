package com.epam.training.webshop.core.gross;

import com.epam.training.webshop.core.cart.ShoppingCartService;

public interface GrossPriceCalculator {

    double getAggregatedGrossPrice(ShoppingCartService shoppingCartService);
}
