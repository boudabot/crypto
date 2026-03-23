export type PayloadMeta = {
  mode: string;
  source: string;
  asOf: string;
};

export type AssetType = "CRYPTO" | "EQUITY" | "MACRO";

export type AssetInfo = {
  type: AssetType;
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

export type Summary = {
  title: string;
  body: string;
};

export type EventItem = {
  timeframe: string;
  title: string;
  detail: string;
};

export type SidebarItem = {
  label: string;
  value: string;
  detail: string;
};

export type SidebarSection = {
  title: string;
  items: SidebarItem[];
};

export type AssetPagePayload = {
  meta: PayloadMeta;
  asset: AssetInfo;
  primaryPrice: PrimaryPrice;
  timeSeries: TimeSeries;
  keyMetrics: MetricItem[];
  summary: Summary;
  events: EventItem[];
  sidebar: SidebarSection[];
};
