package com.localbrief.service;

import com.localbrief.model.SeriesPoint;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class FredClient {
    private static final String BASE_URL = "https://fred.stlouisfed.org/graph/fredgraph.csv?id=";

    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public List<SeriesPoint> fetchSeries(String seriesId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + seriesId))
                .timeout(Duration.ofSeconds(20))
                .header("Accept", "text/csv")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        if (response.statusCode() >= 400) {
            throw new IOException("FRED returned HTTP " + response.statusCode() + " for " + seriesId);
        }

        List<SeriesPoint> points = new ArrayList<>();
        String[] lines = response.body().split("\\R");
        for (int index = 1; index < lines.length; index++) {
            String line = lines[index].trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(",", 2);
            if (parts.length < 2 || ".".equals(parts[1])) {
                continue;
            }

            LocalDate date = LocalDate.parse(parts[0]);
            BigDecimal value = new BigDecimal(parts[1]);
            points.add(new SeriesPoint(date, value));
        }

        points.sort(Comparator.comparing(SeriesPoint::date));
        return points;
    }
}

