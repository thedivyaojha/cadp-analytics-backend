package com.cadp.portal.analytics.ingestion.dsp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

public record DspRow(String isrc,
                     String platform,
                     YearMonth reportMonth,
                     long quantity,
                     BigDecimal revenueUsd,
                     String rawJson) {
}
