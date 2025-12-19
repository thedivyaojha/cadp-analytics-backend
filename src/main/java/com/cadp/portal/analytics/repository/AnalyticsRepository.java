package com.cadp.portal.analytics.repository;

import com.cadp.portal.analytics.util.Platform;
import com.cadp.portal.analytics.entity.Analytics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.Optional;
import java.util.UUID;

public interface AnalyticsRepository extends JpaRepository<Analytics, UUID> {

    Optional<Analytics> findBySong_IsrcAndPlatformAndReportMonth(
            String isrc,
            Platform platform,
            YearMonth reportMonth
    );
}
