package com.epam.training.webshop.ui.configuration;

import com.epam.training.webshop.core.cart.Cart;
import com.epam.training.webshop.core.cart.grossprice.GrossPriceCalculator;
import com.epam.training.webshop.core.cart.grossprice.impl.GrossPriceCalculatorImpl;
import com.epam.training.webshop.core.cart.grossprice.impl.HungarianTaxGrossPriceDecorator;
import com.epam.training.webshop.core.finance.bank.Bank;
import com.epam.training.webshop.core.product.ProductService;
import com.epam.training.webshop.core.product.ProductServiceImpl;
import com.epam.training.webshop.ui.command.AbstractCommand;
import com.epam.training.webshop.ui.interpreter.CommandLineInterpreter;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Bank bank() {
        return new Bank();
    }

    @Bean
    public Cart cart(Bank bank) {
        return Cart.createEmptyCart(bank);
    }

    @Bean(initMethod = "initProducts")
    public ProductService productService() {
        return new ProductServiceImpl();
    }

    @Bean
    public GrossPriceCalculator grossPriceCalculator() {
        return new HungarianTaxGrossPriceDecorator(new GrossPriceCalculatorImpl());
    }

    @Bean
    public CommandLineInterpreter commandLineInterpreter(Set<AbstractCommand> abstractCommands) {
        return new CommandLineInterpreter(abstractCommands);
    }
}
