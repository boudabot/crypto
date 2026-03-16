import type { AssetPagePayload } from "../contracts/assetPage";

export const mockAssetPagePayload: AssetPagePayload = {
  meta: {
    mode: "Offline / Mock",
    source: "Desktop shell local",
    asOf: "2026-03-16T08:30:00Z",
  },
  asset: {
    type: "CRYPTO",
    symbol: "BTC",
    name: "Bitcoin",
    pair: "BTC / USD",
    venue: "Local Desktop Demo",
  },
  primaryPrice: {
    displayValue: "$69,820",
    rawValue: 69820,
    changePercent: 2.64,
    changeDisplay: "+2.64 %",
    rangeDisplay: "$67.9k - $70.3k",
    context: "Regime de leader conserve dans cette demo locale.",
  },
  timeSeries: {
    timeframe: "3W",
    points: [
      { time: "2026-02-24T18:00:00Z", label: "24 Feb", value: 65220 },
      { time: "2026-02-26T18:00:00Z", label: "26 Feb", value: 65810 },
      { time: "2026-02-28T18:00:00Z", label: "28 Feb", value: 66100 },
      { time: "2026-03-02T18:00:00Z", label: "02 Mar", value: 67140 },
      { time: "2026-03-04T18:00:00Z", label: "04 Mar", value: 67580 },
      { time: "2026-03-06T18:00:00Z", label: "06 Mar", value: 67890 },
      { time: "2026-03-08T18:00:00Z", label: "08 Mar", value: 68740 },
      { time: "2026-03-10T18:00:00Z", label: "10 Mar", value: 68990 },
      { time: "2026-03-12T18:00:00Z", label: "12 Mar", value: 70120 },
      { time: "2026-03-15T18:00:00Z", label: "15 Mar", value: 69820 },
    ],
  },
  keyMetrics: [
    { label: "Spot", value: "$69.82k", detail: "Prix demo local" },
    { label: "Variation 24 h", value: "+2.64 %", detail: "Momentum propre" },
    { label: "Range 24 h", value: "$67.9k - $70.3k", detail: "Volatilite contenue" },
    { label: "Volume 24 h", value: "$32.4B", detail: "Flux du leader crypto" },
    { label: "Market cap", value: "$1.38T", detail: "Poids majeur du marche" },
    { label: "Dominance BTC", value: "59.2 %", detail: "Regime encore centre BTC" },
  ],
  summary: {
    title: "Resume / narratif",
    body:
      "Ce payload sert de contrat minimal entre le shell frontend moderne et le futur backend Java local. La page reste 100 % mock, mais toutes les zones structurelles existent deja dans une forme stable et transmissible.",
  },
  events: [
    {
      timeframe: "Maintenant",
      title: "Contrat d'echange stabilise",
      detail:
        "Le frontend n'attend plus un objet ad hoc, mais un payload page actif explicite et extensible.",
    },
    {
      timeframe: "Etape suivante",
      title: "Factory Java locale",
      detail:
        "Le backend pourra construire ce meme payload localement avant toute vraie exposition ou integration desktop.",
    },
    {
      timeframe: "Plus tard",
      title: "APIs live",
      detail:
        "Les integrations crypto, macro et news rempliront ce contrat sans changer la structure de page.",
    },
  ],
  sidebar: [
    {
      title: "Session",
      items: [
        { label: "Mode", value: "Offline / Mock", detail: "Aucune API branchee" },
        { label: "Source", value: "Desktop shell local", detail: "Payload purement local" },
      ],
    },
    {
      title: "Contexte",
      items: [
        { label: "Frontend", value: "React + TypeScript", detail: "Shell moderne Windows" },
        { label: "Backend futur", value: "Java local", detail: "Moteur data/metier a connecter ensuite" },
      ],
    },
  ],
};
