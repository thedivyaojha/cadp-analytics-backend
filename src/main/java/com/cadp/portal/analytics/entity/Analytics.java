package com.cadp.portal.analytics.entity;

import com.cadp.portal.analytics.util.Platform;
import com.cadp.portal.analytics.util.RawDataConverter;
import com.cadp.portal.analytics.domain.song.Song;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(
        name = "analytics",
        uniqueConstraints = @UniqueConstraint(
                        name = "uk_analytics_isrc_platform_month",
                        columnNames = {"isrc", "platform", "report_month"}
                )
        ,
        indexes = {
                @Index(name = "idx_analytics_month", columnList = "report_month"),
                @Index(name = "idx_analytics_platform", columnList = "platform")
        }
)
public class Analytics {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "isrc", nullable = false)
    private Song song;

    @Column(name = "report_month", nullable = false)
    private YearMonth reportMonth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Platform platform;

    @Column(name = "revenue_inr", nullable = false, precision = 19, scale = 4)
    private BigDecimal revenueInr;

    @Column(nullable = false)
    private Long quantity;

    @Column(
            name = "raw_data",
            columnDefinition = "jsonb"
    )
    @Convert(converter = RawDataConverter.class)
    private Map<String, Object> rawData;

    // ----- Constructors -----

    public Analytics() {
        // JPA
    }

    public Analytics(
            Song song,
            YearMonth reportMonth,
            Platform platform,
            BigDecimal revenueInr,
            Long quantity,
            Map<String, Object> rawData
    ) {
        this.song = song;
        this.reportMonth = reportMonth;
        this.platform = platform;
        this.revenueInr = revenueInr;
        this.quantity = quantity;
        this.rawData = rawData;
    }

    public Analytics(Song song, YearMonth reportMonth, String platform, BigDecimal revenueInr, long quantity, Map<String, Object> rawData) {
    }

    // ----- Getters -----

    public UUID getId() {
        return id;
    }

    public Song getSong() {
        return song;
    }

    public YearMonth getReportMonth() {
        return reportMonth;
    }

    public Platform getPlatform() {
        return platform;
    }

    public BigDecimal getRevenueInr() {
        return revenueInr;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Map<String, Object> getRawData() {
        return rawData;
    }


}
