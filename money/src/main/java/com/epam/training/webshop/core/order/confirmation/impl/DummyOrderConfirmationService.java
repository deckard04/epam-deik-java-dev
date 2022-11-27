package com.epam.training.webshop.core.order.confirmation.impl;

import com.epam.training.webshop.core.cart.Cart;
import com.epam.training.webshop.core.order.confirmation.OrderConfirmationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DummyOrderConfirmationService implements OrderConfirmationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyOrderConfirmationService.class);

    @Override
    public void sendOrderConfirmation(final Cart cart) {
        LOGGER.info("An order confirmation for cart {} had been sent.", cart);
    }

    @Override
    public void notify(final Cart cart) {
        sendOrderConfirmation(cart);
    }
}
