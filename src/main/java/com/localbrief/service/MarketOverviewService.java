package com.localbrief.service;

import com.localbrief.model.IndicatorSnapshot;
import com.localbrief.model.MarketOverview;
import com.localbrief.model.SeriesPoint;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public final class MarketOverviewService {
    private final FredClient fredClient;

    public MarketOverviewService() {
        this(new FredClient());
    }

    MarketOverviewService(FredClient fredClient) {
        this.fredClient = fredClient;
    }

    public MarketOverview fetchOverview() throws IOException, InterruptedException {
        try {
            return buildOverviewFromRemote();
        } catch (IOException exception) {
            return buildOverviewFromSample("Mode demo hors ligne");
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            return buildOverviewFromSample("Mode demo hors ligne");
        }
    }

    private MarketOverview buildOverviewFromRemote() throws IOException, InterruptedException {
        List<IndicatorSnapshot> indicators = new ArrayList<>();

        indicators.add(buildInflationSnapshot());
        indicators.add(buildStandardSnapshot(
                "Chomage",
                "Taux de chomage civil",
                "%",
                fredClient.fetchSeries("UNRATE"),
                1
        ));
        indicators.add(buildStandardSnapshot(
                "Taux directeur",
                "Fed Funds Rate",
                "%",
                fredClient.fetchSeries("FEDFUNDS"),
                1
        ));
        indicators.add(buildStandardSnapshot(
                "Taux 10 ans",
                "Rendement du Treasury 10Y",
                "%",
                fredClient.fetchSeries("DGS10"),
                20
        ));
        indicators.add(buildStandardSnapshot(
                "Emploi",
                "Emplois non agricoles",
                "M",
                scaleSeries(fredClient.fetchSeries("PAYEMS"), new BigDecimal("1000")),
                1
        ));

        return new MarketOverview(Instant.now(), indicators, buildNarrative(indicators), "Donnees FRED en direct");
    }

    private MarketOverview buildOverviewFromSample(String sourceLabel) {
        List<IndicatorSnapshot> indicators = new ArrayList<>();
        indicators.add(buildStandardSnapshot(
                "Inflation",
                "Variation annuelle du CPI",
                "%",
                SampleSeriesRepository.inflationSeries(),
                12
        ));
        indicators.add(buildStandardSnapshot(
                "Chomage",
                "Taux de chomage civil",
                "%",
                SampleSeriesRepository.unemploymentSeries(),
                1
        ));
        indicators.add(buildStandardSnapshot(
                "Taux directeur",
                "Fed Funds Rate",
                "%",
                SampleSeriesRepository.fedFundsSeries(),
                1
        ));
        indicators.add(buildStandardSnapshot(
                "Taux 10 ans",
                "Rendement du Treasury 10Y",
                "%",
                SampleSeriesRepository.treasurySeries(),
                20
        ));
        indicators.add(buildStandardSnapshot(
                "Emploi",
                "Emplois non agricoles",
                "M",
                SampleSeriesRepository.payrollsSeries(),
                1
        ));

        return new MarketOverview(
                Instant.now(),
                indicators,
                "Connexion distante indisponible. L'application affiche un jeu de donnees local pour que la v1 "
                        + "se lance immediatement. " + buildNarrative(indicators),
                sourceLabel
        );
    }

    private IndicatorSnapshot buildInflationSnapshot() throws IOException, InterruptedException {
        List<SeriesPoint> cpi = fredClient.fetchSeries("CPIAUCSL");
        if (cpi.size() < 13) {
            throw new IOException("Not enough CPI data to compute yearly inflation.");
        }

        List<SeriesPoint> yoyPoints = new ArrayList<>();
        for (int i = 12; i < cpi.size(); i++) {
            BigDecimal current = cpi.get(i).value();
            BigDecimal previousYear = cpi.get(i - 12).value();
            BigDecimal yoy = current.subtract(previousYear)
                    .divide(previousYear, 6, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
            yoyPoints.add(new SeriesPoint(cpi.get(i).date(), yoy));
        }

        return buildStandardSnapshot(
                "Inflation",
                "Variation annuelle du CPI",
                "%",
                yoyPoints,
                12
        );
    }

    private IndicatorSnapshot buildStandardSnapshot(
            String title,
            String description,
            String unit,
            List<SeriesPoint> points,
            int comparisonStep
    ) {
        List<SeriesPoint> trimmed = trim(points, 36);
        SeriesPoint latest = trimmed.get(trimmed.size() - 1);
        SeriesPoint comparison = trimmed.get(Math.max(0, trimmed.size() - 1 - comparisonStep));
        BigDecimal delta = latest.value().subtract(comparison.value()).setScale(2, RoundingMode.HALF_UP);

        return new IndicatorSnapshot(
                title,
                description,
                unit,
                latest.value().setScale(2, RoundingMode.HALF_UP),
                latest.date(),
                delta,
                buildTrendLabel(delta),
                trimmed
        );
    }

    private List<SeriesPoint> scaleSeries(List<SeriesPoint> points, BigDecimal divisor) {
        List<SeriesPoint> scaled = new ArrayList<>(points.size());
        for (SeriesPoint point : points) {
            scaled.add(new SeriesPoint(
                    point.date(),
                    point.value().divide(divisor, 3, RoundingMode.HALF_UP)
            ));
        }
        return scaled;
    }

    private List<SeriesPoint> trim(List<SeriesPoint> points, int size) {
        int fromIndex = Math.max(0, points.size() - size);
        return new ArrayList<>(points.subList(fromIndex, points.size()));
    }

    private String buildTrendLabel(BigDecimal delta) {
        int sign = delta.compareTo(BigDecimal.ZERO);
        if (sign > 0) {
            return "hausse";
        }
        if (sign < 0) {
            return "baisse";
        }
        return "stable";
    }

    private String buildNarrative(List<IndicatorSnapshot> indicators) {
        IndicatorSnapshot inflation = indicators.get(0);
        IndicatorSnapshot unemployment = indicators.get(1);
        IndicatorSnapshot fedFunds = indicators.get(2);
        IndicatorSnapshot treasury = indicators.get(3);
        IndicatorSnapshot payrolls = indicators.get(4);

        String inflationTone = inflation.latestValue().compareTo(new BigDecimal("3.00")) > 0
                ? "reste au-dessus d'un rythme confortable"
                : "semble revenir vers une zone plus maitrisable";
        String laborTone = unemployment.latestValue().compareTo(new BigDecimal("4.50")) > 0
                ? "Le marche du travail ralentit un peu"
                : "Le marche du travail reste solide";
        String rateTone = fedFunds.deltaValue().compareTo(BigDecimal.ZERO) > 0
                ? "La Fed durcit encore sa posture."
                : fedFunds.deltaValue().compareTo(BigDecimal.ZERO) < 0
                ? "La Fed assouplit sa posture."
                : "La Fed reste en pause.";

        return "Vue rapide: l'inflation " + inflationTone + ", le chomage est a "
                + inflationSafe(unemployment.latestValue()) + "%, le 10 ans est a "
                + inflationSafe(treasury.latestValue()) + "% et l'emploi total atteint "
                + inflationSafe(payrolls.latestValue()) + " M. " + laborTone + ". " + rateTone;
    }

    private String inflationSafe(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }
}
