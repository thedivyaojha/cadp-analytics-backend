package com.cadp.portal.analytics.repository;
import com.cadp.portal.analytics.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.YearMonth;
import java.util.Optional;
import java.util.UUID;
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, UUID> {
    Optional<ExchangeRate> findByMonth(YearMonth month);
}
