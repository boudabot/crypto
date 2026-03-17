# PRODUCT TARGET — CURRENT

## Purpose
This file defines the **current product target** for the repository.
It is not a long-term fantasy architecture document.
It is the **practical target Codex must build toward now**.

## Current repo reality
- A **local Java desktop application** already exists at the repository root.
- It must remain the **runnable source of truth** during the transition.
- The project also contains a **React + TypeScript + Tauri shell** under `desktop/`.
- That modern shell is a **parallel direction**, not a replacement that may break the current app.
- The repository uses **Maven**.
- The repository targets **Java 21**.
- Do not assume any full migration is already approved or complete.

## Product target for the current phase
Build a **reliable local portfolio and operations foundation** for crypto first, while preserving the existing runnable Java application.

The current phase is **not**:
- a full live-trading terminal;
- a full rewrite of the app;
- an API-heavy architecture;
- a large UI refactor for style only;
- a speculative over-engineered backend.

## Primary user goal
Allow the user to track real holdings and operations locally with enough precision to answer:
- what is held;
- at what average cost;
- what fees were paid;
- what the portfolio is worth now;
- what the latent and realized PnL are.

## Core outcomes expected in this phase
1. A clean local **ledger model** exists.
2. Manual trade entry is possible or easy to wire.
3. Portfolio calculations are reliable.
4. Fees are never ignored.
5. The current Java app still runs.
6. The code structure stays simple and understandable.
7. Future CSV import and read-only exchange sync remain possible without redesigning everything.

## Scope in
- local portfolio model;
- trade / fee / position logic;
- weighted average cost basis;
- realized and unrealized PnL foundations;
- manual entry flow;
- portfolio summary view;
- journal / operations view;
- minimal desktop UI to inspect holdings and operations;
- preparation for Binance CSV import.

## Scope out for now
- auto-trading;
- leverage / derivatives;
- complex order management;
- notification systems;
- live multi-provider market infrastructure;
- full Tauri migration;
- advanced charting before portfolio foundations are correct;
- architecture abstractions without immediate use.

## Product priorities
1. Data correctness.
2. Runnable app.
3. Clear model.
4. Simple UI that exposes truth.
5. CSV import path.
6. Modern shell integration later.

## Minimum screens or modules that matter now
### 1. Portfolio overview
Must show at least:
- asset;
- quantity;
- average price;
- total cost;
- fees;
- current value;
- unrealized PnL.

### 2. Operations journal
Must show at least:
- date/time;
- buy/sell;
- asset;
- quantity;
- unit price;
- fee;
- fee currency;
- source;
- note if present.

### 3. Trade entry flow
Must allow recording a new operation with enough information to recalculate portfolio truth.

## Definition of success for the current phase
The app can locally represent a small real crypto portfolio, calculate weighted average cost correctly, include fees, survive partial sales, and expose the result in a readable UI without breaking the existing Java app.

## Mandatory implementation mindset
- prefer incremental change;
- preserve current runnable behavior;
- keep classes small;
- no speculative architecture;
- distinguish clearly between **current implementation** and **future direction**.
