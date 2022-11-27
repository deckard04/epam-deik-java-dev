package com.epam.training.webshop.core.order.confirmation;

import com.epam.training.webshop.core.cart.Cart;
import com.epam.training.webshop.core.order.Observer;

public interface OrderConfirmationService extends Observer {

    void sendOrderConfirmation(Cart cart);
}
