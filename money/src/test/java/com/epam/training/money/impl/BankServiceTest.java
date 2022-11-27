package com.epam.training.money.impl;

import com.epam.training.webshop.core.financial.MonetaryValueConversionService;
import com.epam.training.webshop.core.financial.exception.UnknownConversionRateException;
import com.epam.training.webshop.core.financial.impl.BankService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankServiceTest {

    private static final Currency HUF_CURRENCY = Currency.getInstance("HUF");
    private static final Currency USD_CURRENCY = Currency.getInstance("USD");
    private static final Currency GBP_CURRENCY = Currency.getInstance("GBP");
    private static final int VALUE_TO_CONVERT = 1000;

    private MonetaryValueConversionService underTest;

    @BeforeEach
    void setUp() {
        underTest = new BankService();
    }

    @Test
    void testConvertShouldReturnConvertedValueWhenGivenHandledConversionRate() {
        // Given
        // When
        final double actual = underTest.convert(VALUE_TO_CONVERT, HUF_CURRENCY, USD_CURRENCY);
        // Then
        Assertions.assertEquals(3.4, actual);
    }

    @Test
    void testConvertShouldReturnGivenValueWhenGivenCurrencyPairIsIdentical() {
        // Given
        // When
        final double actual = underTest.convert(VALUE_TO_CONVERT, HUF_CURRENCY, HUF_CURRENCY);
        // Then
        assertEquals(VALUE_TO_CONVERT, actual);
    }

    @Test
    void testConvertShouldThrowUnknownConversionRateExceptionWhenGivenNotHandledCurrencyPair() {
        // Given
        // When - Then
        Assertions.assertThrows(UnknownConversionRateException.class,
                () -> underTest.convert(VALUE_TO_CONVERT, HUF_CURRENCY, GBP_CURRENCY));
    }
}
