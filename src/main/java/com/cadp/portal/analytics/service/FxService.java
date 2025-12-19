package com.cadp.portal.analytics.service;

import com.cadp.portal.analytics.repository.ExchangeRateRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;

@Service
public class FxService {

    private final ExchangeRateRepository exchangeRateRepository;

    public FxService(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public BigDecimal usdToInr(YearMonth month, BigDecimal usdAmount) {
        if (usdAmount == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal rate = exchangeRateRepository
                .findByMonth(month)
                .orElseThrow(() ->
                        new IllegalStateException("Missing FX rate for month: " + month)
                )
                .getUsdToInr();

        return usdAmount.multiply(rate);
    }
}
