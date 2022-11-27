package com.epam.training.webshop.core.order.dto;

import com.epam.training.webshop.core.financial.model.Money;
import com.epam.training.webshop.core.product.dto.ProductDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
@Builder
public class OrderDTO {

    private final List<ProductDTO> orderedProducts;
    private final Money totalNetPrice;
    private final Money totalGrossPrice;

    @Override
    public String toString() {
        return String.format("TotalNetPrice:%s %s, totalGrossPrice:%s %s, products: [%s]",
                totalNetPrice.getAmount(), totalNetPrice.getCurrency(),
                totalGrossPrice.getAmount(), totalGrossPrice.getCurrency(),
                orderedProducts
        );
    }
}
