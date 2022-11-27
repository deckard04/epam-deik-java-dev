package com.epam.training.webshop.presentation.cli.handler;

import com.epam.training.webshop.core.cart.ShoppingCartService;
import com.epam.training.webshop.core.order.OrderService;
import com.epam.training.webshop.core.product.ProductService;
import com.epam.training.webshop.core.product.dto.ProductDTO;
import com.epam.training.webshop.core.product.exception.UnknownProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;


@ShellComponent
@RequiredArgsConstructor
public class CartCommandHandler {

    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;
    private final ProductService productService;

    @ShellMethod(key = "user cart list", value = "List the cart content")
    public String cartList() {
        if (shoppingCartService.getProducts().isEmpty()) {
            return "The cart is empty!";
        } else {
            return shoppingCartService.getProducts().toString();
        }
    }

    @ShellMethod(key = "user cart checkout", value = "Checkout the cart")
    public String cartCheckout() {
        if (shoppingCartService.getProducts().isEmpty()) {
            return "You cannot checkout because your cart is empty!";
        } else {
            final String order = "Your order: " + orderService.order(shoppingCartService);
            shoppingCartService.clearCart();
            return order;
        }
    }

    @ShellMethod(key = "user cart clear", value = "Clear the cart")
    public String cartClear() {
        if (shoppingCartService.getProducts().isEmpty()) {
            return "You cannot clear your cart because it is empty!";
        } else {
            shoppingCartService.clearCart();
            return "The cart is cleared successfully!";
        }
    }

    @ShellMethod(key = "user add product", value = "Add product to cart")
    public String addProduct(final String productName) {
        try {
            final ProductDTO foundProduct = productService.getProductByName(productName);
            shoppingCartService.addProduct(foundProduct);
            return productName + " is added to your cart!";
        } catch (UnknownProductException e) {
            return productName + " is not found as a Product!";
        }
    }

    @ShellMethod(key = "user remove product", value = "Remove product from cart")
    public String removeProduct(final String productName) {
        try {
            final ProductDTO foundProduct = productService.getProductByName(productName);
            if (shoppingCartService.getProducts().contains(foundProduct)) {
                shoppingCartService.removeProduct(foundProduct);
                return productName + " is removed from your cart!";
            } else {
                return productName + " is not in your cart!";
            }
        } catch (UnknownProductException e) {
            return productName + " is not found as a Product!";
        }
    }
}
