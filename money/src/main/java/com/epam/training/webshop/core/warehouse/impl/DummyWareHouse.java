package com.epam.training.webshop.core.warehouse.impl;

import com.epam.training.webshop.core.cart.Cart;
import com.epam.training.webshop.core.product.dto.ProductDTO;
import com.epam.training.webshop.core.warehouse.WareHouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DummyWareHouse implements WareHouse {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyWareHouse.class);

    @Override
    public void registerOrderedProducts(final List<ProductDTO> products) {
        LOGGER.info("I have registered the order of products {}", products);
    }

    @Override
    public void notify(final Cart cart) {
        registerOrderedProducts(cart.getProducts());
    }
}
