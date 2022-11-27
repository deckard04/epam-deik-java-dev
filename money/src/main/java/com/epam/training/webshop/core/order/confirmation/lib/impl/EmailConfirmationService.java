package com.epam.training.webshop.core.order.confirmation.lib.impl;

import com.epam.training.webshop.core.order.confirmation.lib.ConfirmationService;
import com.epam.training.webshop.core.product.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EmailConfirmationService implements ConfirmationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailConfirmationService.class);

    @Override
    public void sendConfirmationMessageAbout(final List<ProductDTO> products) {
        LOGGER.info("Sending an e-mail confirmation about {} products", products);
    }
}
