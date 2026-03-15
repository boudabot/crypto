package com.localbrief.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record IndicatorSnapshot(
        String title,
        String description,
        String unit,
        BigDecimal latestValue,
        LocalDate latestDate,
        BigDecimal deltaValue,
        String trendLabel,
        List<SeriesPoint> points
) {
}

