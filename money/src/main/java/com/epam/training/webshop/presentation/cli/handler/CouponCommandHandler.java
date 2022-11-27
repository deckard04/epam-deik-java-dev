package com.epam.training.webshop.presentation.cli.handler;

import com.epam.training.webshop.core.cart.ShoppingCartService;
import com.epam.training.webshop.core.cart.exception.CouponNotUseableException;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class CouponCommandHandler {

    private final ShoppingCartService shoppingCartService;

    @ShellMethod(value = "Use Coupon for products in the Cart", key = "user add coupon")
    public String addCouponToCart(final String couponName) {
        try {
            shoppingCartService.addCoupon(couponName);
            return "Added coupon to the cart.";
        } catch (CouponNotUseableException e) {
            return "Coupon is not applicable";
        }
    }
}
