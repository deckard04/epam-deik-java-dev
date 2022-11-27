package com.epam.training.webshop.core.product.impl;

import com.epam.training.webshop.core.product.ProductService;
import com.epam.training.webshop.core.product.converter.ProductDTOToEntityConverter;
import com.epam.training.webshop.core.product.converter.ProductEntityToDTOConverter;
import com.epam.training.webshop.core.product.dto.ProductDTO;
import com.epam.training.webshop.core.product.exception.UnknownProductException;
import com.epam.training.webshop.core.product.persistence.entitiy.Product;
import com.epam.training.webshop.core.product.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductEntityToDTOConverter dtoConverter;
    private final ProductDTOToEntityConverter entityConverter;

    @Override
    public List<ProductDTO> getProductList() {
        return ((List<Product>) productRepository.findAll()).stream()
                .map(dtoConverter::apply)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductByName(final String productName) {
        return productRepository.findProductByName(productName)
                .map(dtoConverter::apply)
                .orElseThrow(() -> {
                    throw new UnknownProductException(productName);
                });
    }

    @Override
    public ProductDTO createProduct(final ProductDTO product) {
        return dtoConverter.apply(
                productRepository.save(
                        entityConverter.apply(product)
                )
        );
    }
}

