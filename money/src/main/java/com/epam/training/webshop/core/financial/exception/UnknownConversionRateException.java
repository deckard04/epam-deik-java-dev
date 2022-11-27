package com.epam.training.webshop.core.financial.exception;

import java.util.Currency;

public class UnknownConversionRateException extends RuntimeException {
  public UnknownConversionRateException(final Currency originalCurrency, final Currency targetCurrency) {
    super("Failed to convert from " + originalCurrency + " to " + targetCurrency);
  }
}
