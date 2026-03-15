package com.localbrief.model;

import java.time.Instant;
import java.util.List;

public record MarketOverview(
        Instant fetchedAt,
        List<IndicatorSnapshot> indicators,
        String narrative,
        String sourceLabel
) {
}
