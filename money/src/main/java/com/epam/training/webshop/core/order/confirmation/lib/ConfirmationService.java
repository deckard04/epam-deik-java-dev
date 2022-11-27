package com.epam.training.webshop.core.order.confirmation.lib;

import com.epam.training.webshop.core.product.dto.ProductDTO;

import java.util.List;

public interface ConfirmationService {

    void sendConfirmationMessageAbout(List<ProductDTO> products);
}
