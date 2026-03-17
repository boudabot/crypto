package com.localbrief.contract;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record AssetPagePayload(
        Meta meta,
        Asset asset,
        PrimaryPrice primaryPrice,
        TimeSeries timeSeries,
        List<MetricItem> keyMetrics,
        Summary summary,
        List<EventItem> events,
        List<SidebarSection> sidebar
) {
    public record Meta(
            String mode,
            String source,
            Instant asOf
    ) {
    }

    public record Asset(
            String type,
            String symbol,
            String name,
            String pair,
            String venue
    ) {
    }

    public record PrimaryPrice(
            String displayValue,
            BigDecimal rawValue,
            BigDecimal changePercent,
            String changeDisplay,
            String rangeDisplay,
            String context
    ) {
    }

    public record TimeSeries(
            String timeframe,
            List<TimeSeriesPoint> points
    ) {
    }

    public record TimeSeriesPoint(
            Instant time,
            String label,
            BigDecimal value
    ) {
    }

    public record MetricItem(
            String label,
            String value,
            String detail
    ) {
    }

    public record Summary(
            String title,
            String body
    ) {
    }

    public record EventItem(
            String timeframe,
            String title,
            String detail
    ) {
    }

    public record SidebarSection(
            String title,
            List<SidebarItem> items
    ) {
    }

    public record SidebarItem(
            String label,
            String value,
            String detail
    ) {
    }
}
