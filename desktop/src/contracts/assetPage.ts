export type PayloadMeta = {
  mode: string;
  source: string;
  asOf: string;
};

export type AssetInfo = {
  type: "CRYPTO" | "EQUITY" | "MACRO";
  symbol: string;
  name: string;
  pair: string;
  venue: string;
};

export type PrimaryPrice = {
  displayValue: string;
  rawValue: number;
  changePercent: number;
  changeDisplay: string;
  rangeDisplay: string;
  context: string;
};

export type TimeSeriesPoint = {
  time: string;
  label: string;
  value: number;
};

export type TimeSeries = {
  timeframe: string;
  points: TimeSeriesPoint[];
};

export type MetricItem = {
  label: string;
  value: string;
  detail: string;
};

export type SummaryBlock = {
  title: string;
  body: string;
};

export type EventItem = {
  timeframe: string;
  title: string;
  detail: string;
};

export type SidebarSection = {
  title: string;
  items: MetricItem[];
};

export type AssetPagePayload = {
  meta: PayloadMeta;
  asset: AssetInfo;
  primaryPrice: PrimaryPrice;
  timeSeries: TimeSeries;
  keyMetrics: MetricItem[];
  summary: SummaryBlock;
  events: EventItem[];
  sidebar: SidebarSection[];
};
