package com.epam.training.webshop.core.order;

import com.epam.training.webshop.core.cart.Cart;

public interface Observer {

    void notify(Cart cart);
}
