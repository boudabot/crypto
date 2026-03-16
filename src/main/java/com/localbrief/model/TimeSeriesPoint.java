package com.localbrief.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TimeSeriesPoint(
        LocalDateTime time,
        BigDecimal value
) {
}
