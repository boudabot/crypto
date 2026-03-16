package com.localbrief.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record AssetPageData(
        Asset asset,
        Instant updatedAt,
        String sourceLabel,
        String modeLabel,
        BigDecimal spotPrice,
        BigDecimal change24hPercent,
        String priceContext,
        List<TimeSeriesPoint> chartPoints,
        List<KeyMetric> keyMetrics,
        List<KeyMetric> sidebarMetrics,
        String summary,
        List<String> analysisPoints,
        List<EventItem> events
) {
}
