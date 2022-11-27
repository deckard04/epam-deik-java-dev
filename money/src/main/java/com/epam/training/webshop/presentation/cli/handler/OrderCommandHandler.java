package com.epam.training.webshop.presentation.cli.handler;

import com.epam.training.webshop.core.order.OrderService;
import com.epam.training.webshop.core.order.dto.OrderDTO;
import com.epam.training.webshop.core.user.UserService;
import com.epam.training.webshop.core.user.model.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@AllArgsConstructor
public class OrderCommandHandler {

    private final OrderService orderService;
    private final UserService userService;

    @ShellMethodAvailability("isLoggedIn")
    @ShellMethod(key = "user order list", value = "List previous orders")
    public String listOrders() {
        final UserDTO loggedInUser = userService.describe().orElse(null);
        final List<OrderDTO> previousOrders = orderService.retrieveOrdersForUser(loggedInUser);
        if (previousOrders.isEmpty()) {
            return "There is no Order history.";
        } else {
            return previousOrders.stream()
                    .map(OrderDTO::toString)
                    .collect(Collectors.joining("\n"));
        }
    }

    private Availability isLoggedIn() {
        return userService.describe().isPresent()
                ? Availability.available()
                : Availability.unavailable("You are not logged in!");
    }
}