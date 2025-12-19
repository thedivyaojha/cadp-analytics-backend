package com.cadp.portal.analytics.ingestion.dsp;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.cadp.portal.analytics.util.PlatformNormalizer;


import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import org.apache.commons.csv.CSVRecord;
import static com.cadp.portal.analytics.ingestion.dsp.DspCsvHeaders.*;

public class DspRowMapper {
    private static final DateTimeFormatter DATE_FMT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static DspRow map(CSVRecord record) {

        String isrc = record.get(ISRC).trim();
        if (isrc.isEmpty()) return null; // skip invalid rows

        String platform = PlatformNormalizer.normalize(record.get(STORE));

        LocalDate parsed = LocalDate.parse(
                record.get(ORIGINAL_STATEMENT_PERIOD),
                DATE_FMT
        );

        YearMonth month = YearMonth.from(parsed);

        long quantity = Long.parseLong(record.get(QUANTITY));

        BigDecimal revenueUsd =
                new BigDecimal(record.get(NET_SHARE_USD));

        return new DspRow(
                isrc,
                platform,
                month,
                quantity,
                revenueUsd,
                record.toMap().toString()
        );
    }
}
