package com.epam.training.webshop.core.financial.impl;

import com.epam.training.webshop.core.financial.ConversionRate;
import com.epam.training.webshop.core.financial.MonetaryValueConversionService;
import com.epam.training.webshop.core.financial.exception.UnknownConversionRateException;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.List;

@Service
public class BankService implements MonetaryValueConversionService {

  private static final double HUF_TO_USD_RATIO = 0.0034;
  private static final double USD_TO_HUF_RATIO = 249.3;
  private static final Currency USD_CURRENCY = Currency.getInstance("USD");
  private static final Currency HUF_CURRENCY = Currency.getInstance("HUF");
  private static final List<ConversionRate> CONVERSION_RATES = List.of(
          new IdenticalConverter(),
          new FixedConverter(USD_CURRENCY, HUF_CURRENCY, USD_TO_HUF_RATIO),
          new FixedConverter(HUF_CURRENCY, USD_CURRENCY, HUF_TO_USD_RATIO)
  );

  @Override
  public double convert(final double value, final Currency originalCurrency, final Currency targetCurrency) {
    return CONVERSION_RATES.stream()
            .filter(rate -> rate.canConvert(originalCurrency, targetCurrency))
            .findFirst()
            .map(conversionRate -> conversionRate.convert(value))
            .orElseThrow(() -> new UnknownConversionRateException(originalCurrency, targetCurrency));
  }
}
