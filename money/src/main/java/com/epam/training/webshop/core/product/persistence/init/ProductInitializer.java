package com.epam.training.webshop.core.product.persistence.init;

import com.epam.training.webshop.core.coupon.persistence.entity.Coupon;
import com.epam.training.webshop.core.coupon.persistence.repository.CouponRepository;
import com.epam.training.webshop.core.product.config.ProductConfigurations;
import com.epam.training.webshop.core.product.persistence.entitiy.Product;
import com.epam.training.webshop.core.product.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
/*
Csak a nem `prod` profil esetén fog létre jönni a Bean.
 */
@Profile("! prod")
@RequiredArgsConstructor
@Slf4j
public class ProductInitializer {

    private static final String HUF_CURRENCY_CODE = "HUF";
    private static final String USD_CURRENCY_CODE = "USD";
    private final ProductConfigurations productConfigurations;
    private final ProductRepository productRepository;
    private final CouponRepository couponRepository;

    @PostConstruct
    public void initProducts() {
        log.info("Save products...");
        final Coupon oneHundredDiscount = new Coupon("100HUF", 100.0D, List.of());
        final Coupon twoHundredDiscount = new Coupon("200HUF", 200.0D, List.of());
        couponRepository.save(oneHundredDiscount);
        couponRepository.save(twoHundredDiscount);
        final Product apple = new Product(productConfigurations.getAppleName(), productConfigurations.getApplePrice(), HUF_CURRENCY_CODE, productConfigurations.getApplePackaging(), List.of(oneHundredDiscount));
        final Product melon = new Product(productConfigurations.getMelonName(), productConfigurations.getMelonPrice(), HUF_CURRENCY_CODE, productConfigurations.getMelonPackaging(), List.of(twoHundredDiscount));
        final Product caviar = new Product(productConfigurations.getCaviarName(), productConfigurations.getCaviarPrice(), HUF_CURRENCY_CODE, productConfigurations.getCaviarPackaging(), List.of(oneHundredDiscount, twoHundredDiscount));
        final Product pum = new Product("Szilva", 1.0D,USD_CURRENCY_CODE, "1kg", List.of());
        productRepository.save(apple);
        productRepository.save(melon);
        productRepository.save(caviar);
        productRepository.save(pum);
        log.info("\tCreated coupons...");
        log.info("\tSaved coupons with products.");

        productRepository.findAll().forEach(System.out::println);
        log.info("Saved products.");
    }
}
