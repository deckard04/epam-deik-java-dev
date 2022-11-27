package com.epam.training.webshop.core.cart.converter;

import com.epam.training.webshop.core.financial.model.Money;
import com.epam.training.webshop.core.order.dto.OrderDTO;
import com.epam.training.webshop.core.order.persistence.entity.Order;
import com.epam.training.webshop.core.product.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OrderEntityToDTOConverter implements Function<Order, OrderDTO> {

    @Override
    public OrderDTO apply(final Order order) {
        return OrderDTO.builder()
                .orderedProducts(order.getOrderItems().stream()
                        .map(orderItem -> ProductDTO.builder()
                                .id(orderItem.getId())
                                .packaging(orderItem.getPackaging())
                                .netPrice(new Money(orderItem.getNetPriceAmount(), Currency.getInstance("HUF")))
                                .build())
                        .collect(Collectors.toList()))
                .totalGrossPrice(new Money(order.getGrossPriceAmount(), Currency.getInstance("HUF")))
                .totalNetPrice(new Money(order.getNetPriceAmount(), Currency.getInstance("HUF")))
                .build();
    }
}
