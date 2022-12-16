package com.epam.training.webshop.core.cart.exception;

public class CouponNotUseableException extends RuntimeException {

    public CouponNotUseableException() {
        super();
    }

    public CouponNotUseableException(final String message) {
        super("Coupon can not be used with name: " + message);
    }
}
