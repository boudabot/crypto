export type ChartPoint = {
  label: string;
  value: number;
};

export type InfoCard = {
  label: string;
  value: string;
  detail: string;
};

export type EventItem = {
  timeframe: string;
  title: string;
  detail: string;
};

export type MockAssetPage = {
  symbol: string;
  name: string;
  pair: string;
  venue: string;
  shellLabel: string;
  spotPrice: string;
  change24h: number;
  range24h: string;
  summary: string;
  metrics: InfoCard[];
  sidebar: InfoCard[];
  events: EventItem[];
  chart: ChartPoint[];
};

export const mockAssetPage: MockAssetPage = {
  symbol: "BTC",
  name: "Bitcoin",
  pair: "BTC / USD",
  venue: "Local Desktop Demo",
  shellLabel: "Dark shell demo local",
  spotPrice: "$69,820",
  change24h: 2.64,
  range24h: "$67.9k - $70.3k",
  summary:
    "Ce shell Tauri + React sert de base visuelle moderne pour la future app desktop locale. La page assume un statut 100 % mock et privilegie la structure produit: header actif, chart central, metriques lisibles, resume et colonne laterale exploitable.",
  metrics: [
    { label: "Spot", value: "$69.82k", detail: "Prix demo local" },
    { label: "Variation 24 h", value: "+2.64 %", detail: "Momentum propre" },
    { label: "Range 24 h", value: "$67.9k - $70.3k", detail: "Volatilite contenue" },
    { label: "Volume 24 h", value: "$32.4B", detail: "Flux du leader crypto" },
    { label: "Market cap", value: "$1.38T", detail: "Poids majeur du marche" },
    { label: "Dominance BTC", value: "59.2 %", detail: "Regime encore centre BTC" },
  ],
  sidebar: [
    { label: "Mode", value: "Offline / Mock", detail: "Aucune API branchee" },
    { label: "Desktop shell", value: "Tauri v2", detail: "Cible moderne Windows locale" },
    { label: "Frontend", value: "React + TS", detail: "Base UI separable du backend Java" },
    { label: "Backend futur", value: "Java local", detail: "Moteur data/metier a connecter plus tard" },
  ],
  events: [
    {
      timeframe: "Maintenant",
      title: "Shell desktop isole du backend",
      detail:
        "Le frontend moderne est volontairement independant du backend Java a cette etape pour eviter de melanger migration UI et migration metier.",
    },
    {
      timeframe: "Etape suivante",
      title: "Contrat de donnees local",
      detail:
        "La prochaine brique logique sera un contrat simple entre frontend et backend Java local, en gardant d'abord des payloads demo.",
    },
    {
      timeframe: "Plus tard",
      title: "APIs crypto, macro et news",
      detail:
        "Les integrations live viendront ensuite, une fois le shell desktop et le format de donnees stabilises.",
    },
  ],
  chart: [
    { label: "24 Feb", value: 65220 },
    { label: "26 Feb", value: 65810 },
    { label: "28 Feb", value: 66100 },
    { label: "02 Mar", value: 67140 },
    { label: "04 Mar", value: 67580 },
    { label: "06 Mar", value: 67890 },
    { label: "08 Mar", value: 68740 },
    { label: "10 Mar", value: 68990 },
    { label: "12 Mar", value: 70120 },
    { label: "15 Mar", value: 69820 },
  ],
};
