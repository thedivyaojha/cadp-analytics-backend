package com.cadp.portal.analytics.ingestion.dsp;

import com.cadp.portal.analytics.util.Platform;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class DspAggregator {

    public static Map<DspAggregationKey, AggregatedValue> aggregate(
            Iterable<DspRow> rows
    ) {
        Map<DspAggregationKey, AggregatedValue> map = new HashMap<>();

        for (DspRow row : rows) {
            if (row == null) continue;

            DspAggregationKey key =
                    new DspAggregationKey(
                            row.isrc(),
                            Platform.fromStore(row.platform()),
                            YearMonth.from(row.reportMonth())
                    );

            map.compute(key, (k, v) -> {
                if (v == null) {
                    return new AggregatedValue(
                            row.quantity(),
                            row.revenueUsd()
                    );
                }
                v.add(row.quantity(), row.revenueUsd());
                return v;
            });
        }
        return map;
    }

    public static class AggregatedValue {
        private long quantity;
        private BigDecimal revenueUsd;

        public AggregatedValue(long quantity, BigDecimal revenueUsd) {
            this.quantity = quantity;
            this.revenueUsd = revenueUsd;
        }

        public void add(long q, BigDecimal r) {
            this.quantity += q;
            this.revenueUsd = this.revenueUsd.add(r);
        }

        public long quantity() {
            return quantity;
        }

        public BigDecimal revenueUsd() {
            return revenueUsd;
        }
    }
}
