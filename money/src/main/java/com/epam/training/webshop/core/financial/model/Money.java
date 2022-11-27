package com.epam.training.webshop.core.financial.model;

import com.epam.training.webshop.core.financial.MonetaryValueConversionService;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Currency;

public class Money {

  private final double amount;
  private final Currency currency;

  public Money(final double amount, final Currency currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public double getAmount() {
    return amount;
  }

  public Currency getCurrency() {
    return currency;
  }

  public Money add(final Money moneyToGive, final MonetaryValueConversionService monetaryValueConversionService) {
    final double convertedMoney = convertMoney(moneyToGive, monetaryValueConversionService);
    return new Money(this.getAmount() + convertedMoney, this.getCurrency());
  }

  public Integer compareTo(final Money money, final MonetaryValueConversionService monetaryValueConversionService) {
    final double convertedMoney = convertMoney(money, monetaryValueConversionService);
    return Double.compare(this.getAmount(), convertedMoney);
  }

  public String getCurrencyCode() {
    return this.currency.getCurrencyCode();
  }

  private double convertMoney(final Money money, final MonetaryValueConversionService monetaryValueConversionService) {
    return monetaryValueConversionService.convert(money.getAmount(), money.getCurrency(), this.getCurrency());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    final Money money = (Money) o;

    return new EqualsBuilder().append(amount, money.amount).append(currency, money.currency).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(amount).append(currency).toHashCode();
  }
}
