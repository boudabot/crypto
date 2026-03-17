import { mockAssetPagePayload } from "./mocks/mockAssetPagePayload";
import "./App.css";

const chartWidth = 880;
const chartHeight = 290;
const chartPadding = 28;

function buildChartGeometry(values: number[]) {
  const min = Math.min(...values);
  const max = Math.max(...values);
  const range = Math.max(1, max - min);
  const points = values.map((value, index) => {
    const x = chartPadding + ((chartWidth - chartPadding * 2) * index) / (values.length - 1);
    const y =
      chartHeight -
      chartPadding -
      ((value - min) / range) * (chartHeight - chartPadding * 2);
    return `${x},${y}`;
  });

  return {
    min,
    max,
    polyline: points.join(" "),
    area: `${chartPadding},${chartHeight - chartPadding} ${points.join(" ")} ${chartWidth - chartPadding},${
      chartHeight - chartPadding
    }`,
  };
}

function App() {
  const page = mockAssetPagePayload;
  const geometry = buildChartGeometry(page.timeSeries.points.map((point) => point.value));
  const trendClass = page.primaryPrice.changePercent >= 0 ? "positive" : "negative";

  return (
    <div className="app-shell">
      <header className="topbar">
        <div>
          <span className="eyebrow">Local Data Brief Desktop</span>
          <h1>Modern desktop shell</h1>
          <p>
            React + TypeScript + Tauri, pilote par un contrat minimal de page actif, sans backend
            Java branche pour l&apos;instant.
          </p>
        </div>
        <div className="status-stack">
          <span className="pill">{page.meta.mode}</span>
          <span className="muted-copy">
            {page.meta.source} • {page.meta.asOf}
          </span>
        </div>
      </header>

      <section className="workspace">
        <div className="main-column">
          <article className="panel hero-panel">
            <div className="asset-header">
              <div>
                <span className="asset-type">{page.asset.type}</span>
                <h2>
                  {page.asset.symbol} <span>{page.asset.name}</span>
                </h2>
                <p>
                  {page.asset.pair} | {page.asset.venue}
                </p>
              </div>
              <div className="price-block">
                <strong>{page.primaryPrice.displayValue}</strong>
                <span className={trendClass}>
                  {page.primaryPrice.changePercent >= 0 ? "+" : ""}
                  {page.primaryPrice.changePercent.toFixed(2)}% | {page.primaryPrice.rangeDisplay}
                </span>
              </div>
            </div>

            <div className="chart-shell">
              <div className="chart-copy">
                <span className="eyebrow">Chart placeholder</span>
                <h3>Zone chart principale</h3>
                <p>
                  {page.primaryPrice.context}
                </p>
              </div>
              <div className="chart-stage">
                <div className="chart-grid" />
                <svg viewBox={`0 0 ${chartWidth} ${chartHeight}`} className="chart-svg" role="img">
                  <defs>
                    <linearGradient id="chartArea" x1="0" y1="0" x2="0" y2="1">
                      <stop offset="0%" stopColor="rgba(84, 201, 255, 0.38)" />
                      <stop offset="100%" stopColor="rgba(84, 201, 255, 0.02)" />
                    </linearGradient>
                  </defs>
                  <polygon points={geometry.area} fill="url(#chartArea)" />
                  <polyline points={geometry.polyline} fill="none" stroke="#7ce9ff" strokeWidth="4" />
                </svg>
                <div className="chart-meta chart-meta-top">
                  <span>${geometry.max.toLocaleString("en-US")}</span>
                  <span>Mock trend</span>
                </div>
                <div className="chart-meta chart-meta-bottom">
                  <span>${geometry.min.toLocaleString("en-US")}</span>
                  <div className="chart-labels">
                    {page.timeSeries.points.map((point) => (
                      <span key={point.label}>{point.label}</span>
                    ))}
                  </div>
                </div>
              </div>
            </div>
          </article>

          <article className="panel">
            <div className="section-head">
              <div>
                <span className="eyebrow">Metrics placeholder</span>
                <h3>Bloc metriques cles</h3>
              </div>
            </div>
            <div className="metrics-grid">
              {page.keyMetrics.map((metric) => (
                <section key={metric.label} className="metric-card">
                  <span>{metric.label}</span>
                  <strong>{metric.value}</strong>
                  <p>{metric.detail}</p>
                </section>
              ))}
            </div>
          </article>

          <div className="content-grid">
            <article className="panel">
              <span className="eyebrow">Narrative placeholder</span>
              <h3>{page.summary.title}</h3>
              <p className="lead-copy">{page.summary.body}</p>
            </article>

            <article className="panel">
              <span className="eyebrow">Events placeholder</span>
              <h3>Evenements / analyse</h3>
              <div className="event-list">
                {page.events.map((event) => (
                  <div key={event.title} className="event-item">
                    <span>{event.timeframe}</span>
                    <strong>{event.title}</strong>
                    <p>{event.detail}</p>
                  </div>
                ))}
              </div>
            </article>
          </div>
        </div>

        <aside className="sidebar panel">
          <span className="eyebrow">Sidebar placeholder</span>
          <h3>Colonne laterale</h3>
          <p className="sidebar-intro">
            Cette colonne pose les futures zones de contexte, etat local, connexions backend et
            modules d&apos;analyse.
          </p>
          <div className="sidebar-stack">
            {page.sidebar.map((section) => (
              <section key={section.title} className="sidebar-section">
                <span className="sidebar-section-title">{section.title}</span>
                <div className="sidebar-stack">
                  {section.items.map((item) => (
                    <section key={item.label} className="sidebar-card">
                      <span>{item.label}</span>
                      <strong>{item.value}</strong>
                      <p>{item.detail}</p>
                    </section>
                  ))}
                </div>
              </section>
            ))}
          </div>
        </aside>
      </section>
    </div>
  );
}

export default App;
