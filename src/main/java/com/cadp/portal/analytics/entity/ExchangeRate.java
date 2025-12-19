package com.cadp.portal.analytics.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name = "exchange_rates",
        uniqueConstraints = @UniqueConstraint(columnNames = "month"))
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private java.util.UUID id;

    @Column(nullable = false)
    private LocalDate month; // always first day of month

    @Column(name = "usd_to_inr", nullable = false, precision = 10, scale = 4)
    private BigDecimal usdToInr;

    protected ExchangeRate() {}

    public ExchangeRate(LocalDate month, BigDecimal usdToInr) {
        this.month = month.withDayOfMonth(1);
        this.usdToInr = usdToInr;
    }

    public LocalDate getMonth() {
        return month;
    }

    public BigDecimal getUsdToInr() {
        return usdToInr;
    }

}
