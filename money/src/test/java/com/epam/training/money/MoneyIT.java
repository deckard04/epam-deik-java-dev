package com.epam.training.money;

import com.epam.training.webshop.core.financial.MonetaryValueConversionService;
import com.epam.training.webshop.core.financial.exception.UnknownConversionRateException;
import com.epam.training.webshop.core.financial.impl.BankService;
import com.epam.training.webshop.core.financial.model.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Currency;

import static java.lang.Integer.signum;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class MoneyIT {

    private static final Currency HUF_CURRENCY = Currency.getInstance("HUF");
    private static final Currency USD_CURRENCY = Currency.getInstance("USD");
    private static final Currency GBP_CURRENCY = Currency.getInstance("GBP");

    private final MonetaryValueConversionService monetaryValueConversionService = new BankService();

    @Test
    void testAddReturnsExpectedResultWhenDifferentCurrencyIsUsed() {
        // Given
        final Money underTest = new Money(120, HUF_CURRENCY);
        final Money moneyToAdd = new Money(1, USD_CURRENCY);

        // When
        final Money result = underTest.add(moneyToAdd, monetaryValueConversionService);

        // Then
        assertThat(result.getAmount(), equalTo(369.3));
        assertThat(result.getCurrency(), equalTo(HUF_CURRENCY));
    }

    @Test
    void testAddReturnsExpectedResultWhenMatchingCurrencyIsUsed() {
        // Given
        final Money underTest = new Money(120, HUF_CURRENCY);
        final Money moneyToAdd = new Money(1, HUF_CURRENCY);

        // When
        final Money result = underTest.add(moneyToAdd, monetaryValueConversionService);

        // Then
        assertThat(result.getAmount(), equalTo(121.0));
        assertThat(result.getCurrency(), equalTo(HUF_CURRENCY));
    }

    @Test
    void testAddReturnsNullWhenCurrencyWithUnknownRateIsUsed() {
        // Given
        final Money underTest = new Money(120, HUF_CURRENCY);
        final Money moneyToAdd = new Money(1, GBP_CURRENCY);

        // When - Then
        Assertions.assertThrows(UnknownConversionRateException.class, () -> underTest.add(moneyToAdd, monetaryValueConversionService));
    }


    @ParameterizedTest
    @CsvSource({"249, 1, -1", "249.3, 1, 0", "250, 0, 1"})
    void testCompareToReturnsExpectedResultWhenDifferentCurrencyIsUsed(final double firstValue, final double secondValue, final int expectedSignum) {
        // Given
        final Money underTest = new Money(firstValue, HUF_CURRENCY);
        final Money moneyToCompareWith = new Money(secondValue, USD_CURRENCY);

        // When
        final Integer result = underTest.compareTo(moneyToCompareWith, monetaryValueConversionService);

        // Then
        assertThat(signum(result), equalTo(expectedSignum));
    }

    @ParameterizedTest
    @CsvSource({"0, 100, -1", "100, 100, 0", "100, 0, 1"})
    void testCompareToReturnsExpectedResultWhenMatchingCurrencyIsUsed(final double firstValue, final double secondValue, final int expectedSignum) {
        // Given
        final Money underTest = new Money(firstValue, HUF_CURRENCY);
        final Money moneyToCompareWith = new Money(secondValue, HUF_CURRENCY);

        // When
        final Integer result = underTest.compareTo(moneyToCompareWith, monetaryValueConversionService);

        // Then
        assertThat(signum(result), equalTo(expectedSignum));
    }

    @Test
    void testCompareToReturnsNullWhenCurrencyWithUnknownRateIsUsed() {
        // Given
        final Money underTest = new Money(120, HUF_CURRENCY);
        final Money moneyToCompareWith = new Money(1, GBP_CURRENCY);

        // When - Then
        Assertions.assertThrows(UnknownConversionRateException.class, () -> underTest.compareTo(moneyToCompareWith, monetaryValueConversionService));
    }

}
