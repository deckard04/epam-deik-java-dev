package com.epam.training.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/* Az alkalmazás belépés pontja. */
@SpringBootApplication
@EnableConfigurationProperties
public class WebShopApplication {

    public static void main(final String[] args) {
        SpringApplication.run(WebShopApplication.class, args);
    }
}
