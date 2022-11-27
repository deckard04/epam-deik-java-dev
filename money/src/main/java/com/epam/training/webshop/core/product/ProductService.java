package com.epam.training.webshop.core.product;


import com.epam.training.webshop.core.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getProductList();

    ProductDTO getProductByName(String productName);

    ProductDTO createProduct(ProductDTO productDTO);
}

