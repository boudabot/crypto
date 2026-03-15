package com.localbrief.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SeriesPoint(LocalDate date, BigDecimal value) {
}

