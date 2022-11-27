package com.epam.training.webshop.core.cart.impl;

import com.epam.training.webshop.core.cart.Cart;
import com.epam.training.webshop.core.cart.ShoppingCartService;
import com.epam.training.webshop.core.cart.exception.CouponNotUseableException;
import com.epam.training.webshop.core.coupon.dto.CouponDTO;
import com.epam.training.webshop.core.financial.impl.BankService;
import com.epam.training.webshop.core.gross.GrossPriceCalculator;
import com.epam.training.webshop.core.product.ProductService;
import com.epam.training.webshop.core.product.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static final Currency HUF_CURRENCY = Currency.getInstance("HUF");

    private final Cart cart;
    private final ProductService productService;
    private final GrossPriceCalculator grossPriceCalculator;
    private final BankService bankService;

    public ShoppingCartServiceImpl(final Cart cart,
                                   final ProductService productService,
                                   @Qualifier("hungarianGrossPriceCalculator") final GrossPriceCalculator grossPriceCalculator,
                                   final BankService bankService) {
        this.cart = cart;
        this.productService = productService;
        this.grossPriceCalculator = grossPriceCalculator;
        this.bankService = bankService;

    }

    @Override
    public List<ProductDTO> getProducts() {
        return Collections.unmodifiableList(cart.getProducts());
    }

    @Override
    public void addProduct(final ProductDTO product) {
        cart.addProduct(product);
    }

    @Override
    public void addProduct(final String productName) {
        cart.addProduct(productService.getProductByName(productName));
    }

    @Override
    public void removeProduct(final ProductDTO productToRemove) {
        cart.removeProduct(productToRemove);
    }

    @Override
    public void addCoupon(final String couponName) {
                getAvailableCoupons()
                .filter(coupon -> coupon.getName().equals(couponName))
                .findFirst()
                .ifPresentOrElse(cart::addCoupon,
                        () -> {
                            throw new CouponNotUseableException(couponName);
                        });
    }

    private Stream<CouponDTO> getAvailableCoupons() {
        return cart.getProducts().stream()
                .map(ProductDTO::getAvailableCoupons)
                .flatMap(Collection::stream)
                .distinct();
    }

    @Override
    public List<CouponDTO> getCouponsFromBasket() {
        return cart.getCouponsFromBasket();
    }

    @Override
    public double getTotalNetPrice() {
        final double basePrice = getBasePrice();
        final double discount = getDiscountForCoupons();
        return basePrice - discount;
    }

    @Override
    public double getTotalGrossPrice() {
        return grossPriceCalculator.getAggregatedGrossPrice(this);
    }

    @Override
    public double getBasePrice() {
        double basePrice = 0;
        for (final ProductDTO currentProduct : cart.getProducts()) {
            basePrice += bankService.convert(
                    currentProduct.getNetPrice().getAmount(),
                    Currency.getInstance(currentProduct.getNetPrice().getCurrencyCode()),
                    HUF_CURRENCY
            );
        }
        return basePrice;
    }

    @Override
    public double getDiscountForCoupons() {
        final List<CouponDTO> availableCoupons = getAvailableCoupons().collect(Collectors.toList());
        double discount = 0;
        for (final CouponDTO coupon : cart.getCouponsFromBasket()) {
            if (availableCoupons.contains(coupon)) {
                discount += coupon.getValue();
            }
        }
        return discount;
    }

    @Override
    public void clearCart() {
        cart.removeAllProducts();
    }

    @Override
    public Cart getCart() {
        return cart;
    }
}
