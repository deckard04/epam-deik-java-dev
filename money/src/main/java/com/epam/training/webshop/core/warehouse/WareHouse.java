package com.epam.training.webshop.core.warehouse;

import com.epam.training.webshop.core.order.Observer;
import com.epam.training.webshop.core.product.dto.ProductDTO;

import java.util.List;

public interface WareHouse extends Observer {

    void registerOrderedProducts(List<ProductDTO> products);
}
