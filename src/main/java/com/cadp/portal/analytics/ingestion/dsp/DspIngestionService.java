package com.cadp.portal.analytics.ingestion.dsp;

import com.cadp.portal.analytics.entity.Analytics;
import com.cadp.portal.analytics.domain.song.Song;
import com.cadp.portal.analytics.repository.AnalyticsRepository;
import com.cadp.portal.analytics.repository.SongRepository;
import com.cadp.portal.analytics.service.FxService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@Service
public class DspIngestionService {

    private final AnalyticsRepository analyticsRepository;
    private final SongRepository songRepository;
    private final FxService fxService;

    public DspIngestionService(
            AnalyticsRepository analyticsRepository,
            SongRepository songRepository,
            FxService fxService
    ) {
        this.analyticsRepository = analyticsRepository;
        this.songRepository = songRepository;
        this.fxService = fxService;
    }

    @Transactional
    public void ingest(InputStream csvStream) throws Exception {

        CSVParser parser = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withTrim()
                .parse(new InputStreamReader(csvStream));

        List<DspRow> rows = parser.getRecords()
                .stream()
                .map(DspRowMapper::map)
                .filter(r -> r != null)
                .toList();

        Map<DspAggregationKey, DspAggregator.AggregatedValue> aggregated =
                DspAggregator.aggregate(rows);

        for (var entry : aggregated.entrySet()) {

            DspAggregationKey key = entry.getKey();
            DspAggregator.AggregatedValue value = entry.getValue();
            Song song = songRepository
                    .findByIsrc(key.isrc())
                    .orElseThrow(() ->
                            new IllegalStateException("Song not found for ISRC: " + key.isrc())
                    );

            YearMonth reportMonth = key.reportMonth();

            var revenueInr = fxService.usdToInr(
                    reportMonth,
                    value.revenueUsd()
            );

            Analytics analytics = analyticsRepository
                    .findBySong_IsrcAndPlatformAndReportMonth(
                            key.isrc(),
                            key.platform(),
                            reportMonth
                    )
                    .orElse(
                            new Analytics(
                                    song,
                                    reportMonth,
                                    key.platform(),
                                    revenueInr,
                                    value.quantity(),
                                    null
                            )
                    );

            analyticsRepository.save(analytics);


        }
    }
}
