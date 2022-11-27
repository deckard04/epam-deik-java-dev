package com.epam.training.webshop.presentation.cli.handler;


import com.epam.training.webshop.core.financial.model.Money;
import com.epam.training.webshop.core.product.ProductService;
import com.epam.training.webshop.core.product.dto.ProductDTO;
import com.epam.training.webshop.core.user.UserService;
import com.epam.training.webshop.core.user.model.UserDTO;
import com.epam.training.webshop.core.user.persistence.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.Currency;
import java.util.List;
import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class ProductCommandHandler {

    private final ProductService productService;
    private final UserService userService;

    @ShellMethod(key = "user product list", value = "List the available products")
    public List<ProductDTO> listProducts() {
        return productService.getProductList();
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "admin product create", value = "Create new product")
    public ProductDTO createProduct(final String name, final String packaging, final Double amount, final String currency) {
        final ProductDTO productDto = ProductDTO.builder()
                .name(name)
                .packaging(packaging)
                .netPrice(new Money(amount, Currency.getInstance(currency)))
                .build();
        productService.createProduct(productDto);
        return productDto;
    }

    private Availability isAvailable() {
        final Optional<UserDTO> user = userService.describe();
        return user.isPresent() && user.get().getRole() == User.Role.ADMIN
                ? Availability.available()
                : Availability.unavailable("You are not an admin!");
    }

}
