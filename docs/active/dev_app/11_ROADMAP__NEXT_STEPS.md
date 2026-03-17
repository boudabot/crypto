# ROADMAP — NEXT STEPS

## Purpose
This file defines the **execution order** Codex should follow for the next product steps.
If a request is ambiguous, use this roadmap to choose the next smallest useful increment.

## Rule
Do not jump ahead if the previous layer is not reliable.

## Step 1 — Audit and stabilize current model
Goal:
- identify current portfolio / trade / market-related classes already present;
- identify reusable code and dead code;
- confirm build and runnable entrypoint.

Done when:
- current structure is mapped;
- compile command is known;
- main runnable path is confirmed;
- risks of breakage are listed.

## Step 2 — Define domain model
Goal:
Create or stabilize the minimum domain model needed for local portfolio truth.

Target entities:
- `Asset`
- `Trade`
- `Fee`
- `Position`
- `PortfolioSnapshot`

Done when:
- fields are explicit;
- responsibilities are clear;
- model is simple and serializable if needed;
- no duplicate competing models remain without explanation.

## Step 3 — Implement calculation engine
Goal:
Implement correct portfolio calculations using weighted average cost.

Must handle:
- buy with fee;
- sell with fee;
- partial sell;
- fee in alternate currency;
- unrealized PnL;
- realized PnL;
- cumulative fees.

Done when:
- deterministic test cases pass;
- calculations are reproducible;
- edge cases are documented.

## Step 4 — Create manual input flow
Goal:
Allow the user to record operations without needing exchange API integration.

Must support:
- date/time;
- buy/sell;
- asset;
- quantity;
- price;
- quote currency;
- fee amount;
- fee currency;
- source;
- optional note.

Done when:
- a user can add at least one buy and one sell operation;
- portfolio recalculates after entry;
- validation prevents obviously invalid data.

## Step 5 — Build portfolio view
Goal:
Expose the portfolio truth in the current Java app.

Must show:
- asset;
- quantity;
- average cost;
- invested cost;
- fees;
- current value;
- unrealized PnL.

Done when:
- the view is readable;
- data comes from the real model;
- no hardcoded fake values remain in the feature.

## Step 6 — Build operations journal view
Goal:
Expose operations history clearly.

Must show:
- chronological operations;
- type;
- asset;
- quantities;
- prices;
- fees;
- source;
- notes if available.

Done when:
- a user can audit how a position was built;
- the journal is consistent with the portfolio view.

## Step 7 — Prepare import boundary
Goal:
Prepare the code for CSV import without overbuilding exchange connectors.

Must define:
- import service boundary;
- mapping rules;
- duplicate handling strategy;
- error reporting strategy.

Done when:
- CSV import can be added without redesigning the core model.

## Step 8 — Add minimal Binance CSV import
Goal:
Import basic spot trade history from Binance CSV into the local ledger.

Done when:
- a small real sample imports correctly;
- duplicate imports are handled safely;
- unsupported lines are surfaced clearly.

## Step 9 — Prepare frontend/backend contract
Goal:
Define the payloads needed for the `desktop/` shell.

Done when:
- DTOs are stable enough for UI work;
- field names are fixed;
- mock and real data paths are clear.

## Step 10 — Expand modern UI only after truth is reliable
Goal:
Use the Tauri/React shell to expose the same portfolio truth, not to invent a second system.

Done when:
- modern UI consumes the same trusted data contract;
- no logic divergence exists between Java root app and desktop shell.

## Anti-roadmap rules
- no API-first approach;
- no full rewrite before model reliability;
- no chart-heavy work before portfolio truth;
- no large abstraction layer without current use;
- no migration that breaks the current Java runnable app.
