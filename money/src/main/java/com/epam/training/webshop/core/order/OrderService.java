package com.epam.training.webshop.core.order;

import com.epam.training.webshop.core.cart.ShoppingCartService;
import com.epam.training.webshop.core.order.dto.OrderDTO;
import com.epam.training.webshop.core.user.model.UserDTO;

import java.util.List;

public interface OrderService {

    OrderDTO order(ShoppingCartService shoppingCartService);

    List<OrderDTO> retrieveOrdersForUser(UserDTO userDto);
}

