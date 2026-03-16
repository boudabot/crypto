package com.localbrief.service;

import com.localbrief.model.Asset;
import com.localbrief.model.AssetPageData;
import com.localbrief.model.AssetType;
import com.localbrief.model.EventItem;
import com.localbrief.model.KeyMetric;
import com.localbrief.model.TimeSeriesPoint;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public final class DemoAssetPageService {
    public AssetPageData loadPage() {
        Asset asset = new Asset(
                AssetType.CRYPTO,
                "BTC",
                "Bitcoin",
                "BTC / USD",
                "Local Demo Feed"
        );

        return new AssetPageData(
                asset,
                Instant.parse("2026-03-15T18:45:00Z"),
                "Jeu de donnees offline/mock",
                "Mode demo local",
                value("69820"),
                value("2.64"),
                "Compression propre au-dessus de 68k avec leadership crypto conserve.",
                chartPoints(),
                keyMetrics(),
                sidebarMetrics(),
                "Bitcoin sert ici de page actif de reference pour la nouvelle direction produit. "
                        + "La configuration demo montre un actif leader qui reprend de l'elan apres une phase "
                        + "de digestion courte, avec un contexte encore favorable aux actifs tres liquides.",
                List.of(
                        "Le regime reste centre sur BTC, pas sur une alt season generalisee.",
                        "Le mouvement parait plus structurel qu'un simple spike de news dans ce scenario demo.",
                        "La page privilegie la lecture produit et la lisibilite avant toute logique de trade."
                ),
                events()
        );
    }

    private List<TimeSeriesPoint> chartPoints() {
        return List.of(
                point(2026, Month.FEBRUARY, 23, 64850),
                point(2026, Month.FEBRUARY, 24, 65220),
                point(2026, Month.FEBRUARY, 25, 64940),
                point(2026, Month.FEBRUARY, 26, 65810),
                point(2026, Month.FEBRUARY, 27, 66340),
                point(2026, Month.FEBRUARY, 28, 66100),
                point(2026, Month.MARCH, 1, 66720),
                point(2026, Month.MARCH, 2, 67140),
                point(2026, Month.MARCH, 3, 66990),
                point(2026, Month.MARCH, 4, 67580),
                point(2026, Month.MARCH, 5, 68110),
                point(2026, Month.MARCH, 6, 67890),
                point(2026, Month.MARCH, 7, 68420),
                point(2026, Month.MARCH, 8, 68740),
                point(2026, Month.MARCH, 9, 69260),
                point(2026, Month.MARCH, 10, 68990),
                point(2026, Month.MARCH, 11, 69480),
                point(2026, Month.MARCH, 12, 70120),
                point(2026, Month.MARCH, 13, 69790),
                point(2026, Month.MARCH, 14, 69540),
                point(2026, Month.MARCH, 15, 69820)
        );
    }

    private List<KeyMetric> keyMetrics() {
        return List.of(
                new KeyMetric("Spot", "$69.82k", "Cloture demo locale"),
                new KeyMetric("Variation 24 h", "+2.64 %", "Extension haussiere propre"),
                new KeyMetric("Range 24 h", "$67.9k - $70.3k", "Volatilite contenue"),
                new KeyMetric("Volume 24 h", "$32.4B", "Flux soutenus sur leader"),
                new KeyMetric("Market cap", "$1.38T", "Poids dominant du marche"),
                new KeyMetric("Dominance BTC", "59.2 %", "Regime toujours centre BTC")
        );
    }

    private List<KeyMetric> sidebarMetrics() {
        return List.of(
                new KeyMetric("Lecture", "Leader crypto", "Actif de reference pour une page produit"),
                new KeyMetric("Horizon", "Swing / structure", "Pas une vue scalping"),
                new KeyMetric("Liquidite", "Elevee", "Actif adapte a une base grand public"),
                new KeyMetric("Source", "Offline demo", "Aucune donnee live a ce stade")
        );
    }

    private List<EventItem> events() {
        return List.of(
                new EventItem(
                        "Cette semaine",
                        "Regime toujours porte par BTC",
                        "Le scenario demo conserve un biais de leadership BTC, ce qui soutient la lisibilite du produit."
                ),
                new EventItem(
                        "Catalyseur a surveiller",
                        "Fenetres macro et flux ETF",
                        "Dans une future version live, ces blocs devront pouvoir croiser donnees prix, agenda et contexte."
                ),
                new EventItem(
                        "Point produit",
                        "Narratif et evenements distincts",
                        "La page separe volontairement les faits de structure, le resume et les evenements affichables."
                )
        );
    }

    private TimeSeriesPoint point(int year, Month month, int day, int price) {
        return new TimeSeriesPoint(
                LocalDateTime.of(year, month, day, 18, 0),
                value(Integer.toString(price))
        );
    }

    private BigDecimal value(String amount) {
        return new BigDecimal(amount);
    }
}
