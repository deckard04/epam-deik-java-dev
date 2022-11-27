package com.epam.training.webshop.core.order.impl;


import com.epam.training.webshop.core.cart.ShoppingCartService;
import com.epam.training.webshop.core.financial.model.Money;
import com.epam.training.webshop.core.gross.GrossPriceCalculator;
import com.epam.training.webshop.core.order.Observer;
import com.epam.training.webshop.core.order.OrderService;
import com.epam.training.webshop.core.order.dto.OrderDTO;
import com.epam.training.webshop.core.order.persistence.entity.Order;
import com.epam.training.webshop.core.order.persistence.entity.OrderItem;
import com.epam.training.webshop.core.order.persistence.repository.OrderRepository;
import com.epam.training.webshop.core.product.dto.ProductDTO;
import com.epam.training.webshop.core.user.UserService;
import com.epam.training.webshop.core.user.model.UserDTO;
import com.epam.training.webshop.core.user.persistence.entity.User;
import com.epam.training.webshop.core.user.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderServiceImpl implements OrderService {

    private static final Currency HUF_CURRENCY = Currency.getInstance("HUF");
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final GrossPriceCalculator grossPriceCalculator;

    private final List<Observer> observers;

    public OrderServiceImpl(final UserService userService, final OrderRepository orderRepository, final UserRepository userRepository,
                            @Qualifier("hungarianGrossPriceCalculator") final GrossPriceCalculator grossPriceCalculator,
                            final List<Observer> observers) {
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.grossPriceCalculator = grossPriceCalculator;
        this.userRepository = userRepository;
        this.observers = observers;
    }

    @Override
    public OrderDTO order(final ShoppingCartService shoppingCartService) {
        final OrderDTO orderDto = new OrderDTO(shoppingCartService.getProducts(),
                new Money(shoppingCartService.getTotalNetPrice(), HUF_CURRENCY),
                new Money(grossPriceCalculator.getAggregatedGrossPrice(shoppingCartService), HUF_CURRENCY)
        );
        final UserDTO userDto = userService.describe().orElseThrow(() -> new IllegalArgumentException("You need to first login!"));
        final User user = userRepository.findByUsername(userDto.getUsername()).orElseThrow(() -> new IllegalArgumentException("No such username!"));
        final Order order = createOrderEntityFromDto(orderDto, user);
        orderRepository.save(order);
        final OrderDTO orderDTO = OrderDTO.builder()
                .orderedProducts(shoppingCartService.getProducts())
                .totalNetPrice(new Money(order.getNetPriceAmount(), HUF_CURRENCY))
                .totalGrossPrice(new Money(grossPriceCalculator.getAggregatedGrossPrice(shoppingCartService), HUF_CURRENCY))
                .build();
        observers.forEach(observer -> observer.notify(shoppingCartService.getCart()));
        return orderDTO;
    }

    private Order createOrderEntityFromDto(final OrderDTO orderDto, final User user) {
        return Order.builder()
                .user(user)
                .orderItems(orderDto.getOrderedProducts().stream()
                        .map(this::createOrderItemFromProduct)
                        .collect(Collectors.toList()))
                .netPriceAmount(orderDto.getTotalNetPrice().getAmount())
                .grossPriceAmount(orderDto.getTotalGrossPrice().getAmount())
                .netPriceCurrencyCode(orderDto.getTotalNetPrice().getCurrency().getCurrencyCode())
                .grossPriceCurrencyCode(orderDto.getTotalGrossPrice().getCurrency().getCurrencyCode())
                .build();
    }

    @Override
    public List<OrderDTO> retrieveOrdersForUser(final UserDTO userDto) {
        return orderRepository.findByUserUsername(userDto.getUsername()).stream()
                .map(this::createOrderDtoFromEntity)
                .collect(Collectors.toList());
    }

    private OrderDTO createOrderDtoFromEntity(final Order order) {
        return new OrderDTO(
                order.getOrderItems().stream().map(this::createProductFromOrderItem).collect(Collectors.toList()),
                new Money(order.getNetPriceAmount(), Currency.getInstance(order.getNetPriceCurrencyCode())),
                new Money(order.getGrossPriceAmount(), Currency.getInstance(order.getGrossPriceCurrencyCode()))
        );
    }

    private ProductDTO createProductFromOrderItem(final OrderItem orderItem) {
        return ProductDTO.builder()
                .name(orderItem.getName())
                .packaging(orderItem.getPackaging())
                .netPrice(new Money(orderItem.getNetPriceAmount(), Currency.getInstance(orderItem.getNetPriceCurrencyCode())))
                .build();
    }

    private OrderItem createOrderItemFromProduct(final ProductDTO productDto) {
        return OrderItem.builder()
                .name(productDto.getName())
                .packaging(productDto.getPackaging())
                .netPriceAmount(productDto.getNetPrice().getAmount())
                .netPriceCurrencyCode(productDto.getNetPrice().getCurrency().getCurrencyCode())
                .build();
    }
}
