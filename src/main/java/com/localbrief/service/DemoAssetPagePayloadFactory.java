package com.localbrief.service;

import com.localbrief.contract.AssetPagePayload;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public final class DemoAssetPagePayloadFactory {
    public AssetPagePayload createBitcoinPayload() {
        return new AssetPagePayload(
                new AssetPagePayload.Meta(
                        "Offline / Mock",
                        "Desktop shell local",
                        Instant.parse("2026-03-16T08:30:00Z")
                ),
                new AssetPagePayload.Asset(
                        "CRYPTO",
                        "BTC",
                        "Bitcoin",
                        "BTC / USD",
                        "Local Desktop Demo"
                ),
                new AssetPagePayload.PrimaryPrice(
                        "$69,820",
                        value("69820"),
                        value("2.64"),
                        "+2.64 %",
                        "$67.9k - $70.3k",
                        "Regime de leader conserve dans cette demo locale."
                ),
                new AssetPagePayload.TimeSeries(
                        "3W",
                        List.of(
                                point("2026-02-24T18:00:00Z", "24 Feb", "65220"),
                                point("2026-02-26T18:00:00Z", "26 Feb", "65810"),
                                point("2026-02-28T18:00:00Z", "28 Feb", "66100"),
                                point("2026-03-02T18:00:00Z", "02 Mar", "67140"),
                                point("2026-03-04T18:00:00Z", "04 Mar", "67580"),
                                point("2026-03-06T18:00:00Z", "06 Mar", "67890"),
                                point("2026-03-08T18:00:00Z", "08 Mar", "68740"),
                                point("2026-03-10T18:00:00Z", "10 Mar", "68990"),
                                point("2026-03-12T18:00:00Z", "12 Mar", "70120"),
                                point("2026-03-15T18:00:00Z", "15 Mar", "69820")
                        )
                ),
                List.of(
                        metric("Spot", "$69.82k", "Prix demo local"),
                        metric("Variation 24 h", "+2.64 %", "Momentum propre"),
                        metric("Range 24 h", "$67.9k - $70.3k", "Volatilite contenue"),
                        metric("Volume 24 h", "$32.4B", "Flux du leader crypto"),
                        metric("Market cap", "$1.38T", "Poids majeur du marche"),
                        metric("Dominance BTC", "59.2 %", "Regime encore centre BTC")
                ),
                new AssetPagePayload.Summary(
                        "Resume / narratif",
                        "Ce payload sert de contrat minimal entre le shell frontend moderne et le futur backend "
                                + "Java local. La page reste 100 % mock, mais toutes les zones structurelles "
                                + "existent deja dans une forme stable et transmissible."
                ),
                List.of(
                        event(
                                "Maintenant",
                                "Contrat d'echange stabilise",
                                "Le frontend n'attend plus un objet ad hoc, mais un payload page actif explicite et extensible."
                        ),
                        event(
                                "Etape suivante",
                                "Factory Java locale",
                                "Le backend pourra construire ce meme payload localement avant toute vraie exposition ou integration desktop."
                        ),
                        event(
                                "Plus tard",
                                "APIs live",
                                "Les integrations crypto, macro et news rempliront ce contrat sans changer la structure de page."
                        )
                ),
                List.of(
                        new AssetPagePayload.SidebarSection(
                                "Session",
                                List.of(
                                        sidebarItem("Mode", "Offline / Mock", "Aucune API branchee"),
                                        sidebarItem("Source", "Desktop shell local", "Payload purement local")
                                )
                        ),
                        new AssetPagePayload.SidebarSection(
                                "Contexte",
                                List.of(
                                        sidebarItem("Frontend", "React + TypeScript", "Shell moderne Windows"),
                                        sidebarItem("Backend futur", "Java local", "Moteur data/metier a connecter ensuite")
                                )
                        )
                )
        );
    }

    private AssetPagePayload.TimeSeriesPoint point(String time, String label, String value) {
        return new AssetPagePayload.TimeSeriesPoint(
                Instant.parse(time),
                label,
                value(value)
        );
    }

    private AssetPagePayload.MetricItem metric(String label, String value, String detail) {
        return new AssetPagePayload.MetricItem(label, value, detail);
    }

    private AssetPagePayload.EventItem event(String timeframe, String title, String detail) {
        return new AssetPagePayload.EventItem(timeframe, title, detail);
    }

    private AssetPagePayload.SidebarItem sidebarItem(String label, String value, String detail) {
        return new AssetPagePayload.SidebarItem(label, value, detail);
    }

    private BigDecimal value(String amount) {
        return new BigDecimal(amount);
    }
}
