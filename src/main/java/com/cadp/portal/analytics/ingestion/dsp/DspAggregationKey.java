package com.cadp.portal.analytics.ingestion.dsp;
import com.cadp.portal.analytics.util.Platform;

import java.time.YearMonth;

public record DspAggregationKey(String isrc,
                                Platform platform,
                                YearMonth reportMonth) {
}
