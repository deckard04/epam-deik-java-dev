package com.epam.training.webshop.core.financial;

import java.util.Currency;

public interface ConversionRate {

  boolean canConvert(Currency originalCurrency, Currency targetCurrency);

  double convert(double value);
}
