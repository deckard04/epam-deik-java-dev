package com.epam.training.webshop.core.financial;

import java.util.Currency;

public interface MonetaryValueConversionService {

  double convert(double value, Currency originalCurrency, Currency targetCurrency);

}
