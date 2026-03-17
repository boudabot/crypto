# FRONTEND / BACKEND CONTRACT — MINIMUM VIABLE VERSION

## Purpose
This document defines the **minimum contract** between:
- the local Java application / backend logic;
- the future or parallel frontend layers, including the `desktop/` shell.

It exists to prevent Codex from inventing ad hoc payloads.

## Contract rules
- Field names must stay stable once adopted.
- Numeric values must be clearly typed and consistently named.
- Currency and fee currency must never be implicit.
- Mock payloads must match real payload structure.
- The contract must reflect the source of truth, not a UI shortcut.

## Canonical DTOs

### AssetSummary
Purpose:
Small summary block for listings or overview widgets.

Fields:
- `symbol`: string
- `name`: string
- `assetType`: string
- `primaryCurrency`: string
- `currentPrice`: number | null
- `change24hPct`: number | null

Example:
```json
{
  "symbol": "BTC",
  "name": "Bitcoin",
  "assetType": "CRYPTO",
  "primaryCurrency": "USDT",
  "currentPrice": 84250.12,
  "change24hPct": 1.83
}
```

### PositionView
Purpose:
Trusted portfolio row for one held asset.

Fields:
- `symbol`: string
- `name`: string
- `quantity`: number
- `averageCost`: number
- `quoteCurrency`: string
- `investedCost`: number
- `cumulativeFees`: number | null
- `feeCurrencyNote`: string | null
- `currentPrice`: number | null
- `currentValue`: number | null
- `unrealizedPnl`: number | null
- `unrealizedPnlPct`: number | null

Example:
```json
{
  "symbol": "ETH",
  "name": "Ethereum",
  "quantity": 0.5231,
  "averageCost": 2734.55,
  "quoteCurrency": "USDT",
  "investedCost": 1430.84,
  "cumulativeFees": 4.12,
  "feeCurrencyNote": "Includes fees paid in BNB converted when available",
  "currentPrice": 2891.10,
  "currentValue": 1512.72,
  "unrealizedPnl": 81.88,
  "unrealizedPnlPct": 5.72
}
```

### PortfolioOverview
Purpose:
Top-level portfolio summary.

Fields:
- `valuationTotal`: number
- `valuationCurrency`: string
- `cashBalance`: number | null
- `cashCurrency`: string | null
- `positions`: array of `PositionView`
- `globalUnrealizedPnl`: number | null
- `globalUnrealizedPnlPct`: number | null
- `globalRealizedPnl`: number | null
- `totalFees`: number | null
- `lastUpdated`: string | null

Example:
```json
{
  "valuationTotal": 4812.44,
  "valuationCurrency": "USDT",
  "cashBalance": 925.00,
  "cashCurrency": "USDT",
  "positions": [],
  "globalUnrealizedPnl": 222.16,
  "globalUnrealizedPnlPct": 4.84,
  "globalRealizedPnl": 31.55,
  "totalFees": 18.42,
  "lastUpdated": "2026-03-17T10:35:00"
}
```

### TradeInput
Purpose:
Input payload used to create a local operation.

Fields:
- `executedAt`: string
- `side`: string
- `symbol`: string
- `quantity`: number
- `unitPrice`: number
- `quoteCurrency`: string
- `feeAmount`: number | null
- `feeCurrency`: string | null
- `source`: string
- `note`: string | null

Rules:
- `side` allowed values: `BUY`, `SELL`
- `executedAt` must be ISO-like and timezone policy must be documented in implementation
- if fee is unknown, keep `feeAmount` null and surface that explicitly downstream

Example:
```json
{
  "executedAt": "2026-03-17T09:45:00",
  "side": "BUY",
  "symbol": "SOL",
  "quantity": 1.25,
  "unitPrice": 131.45,
  "quoteCurrency": "USDT",
  "feeAmount": 0.16,
  "feeCurrency": "USDT",
  "source": "MANUAL",
  "note": "Test order"
}
```

### TradeView
Purpose:
Displayable enriched operation.

Fields:
- `id`: string
- `executedAt`: string
- `side`: string
- `symbol`: string
- `quantity`: number
- `unitPrice`: number
- `quoteCurrency`: string
- `grossAmount`: number
- `feeAmount`: number | null
- `feeCurrency`: string | null
- `source`: string
- `note`: string | null

### MarketSnapshot
Purpose:
Minimal market payload for an asset page or price card.

Fields:
- `symbol`: string
- `currentPrice`: number | null
- `currency`: string
- `change24hPct`: number | null
- `series`: array
- `keyMetrics`: object

### AssetPagePayload
Purpose:
Single payload for a modern asset page.

Fields:
- `summary`: `AssetSummary`
- `market`: `MarketSnapshot`
- `position`: `PositionView | null`
- `events`: array
- `notes`: array

## Error shape
If an operation fails, prefer a simple stable structure:

```json
{
  "code": "VALIDATION_ERROR",
  "message": "fee currency is missing",
  "details": {
    "field": "feeCurrency"
  }
}
```

## Calculation rules that UI must not reinterpret
- portfolio cost basis uses weighted average cost by default;
- buy fees are included in acquisition cost;
- sell fees affect realized PnL;
- fee currency must be preserved if conversion is missing or estimated;
- UI must display uncertainty explicitly instead of inventing a conversion.

## Mocking rule
Any mock data used by frontend work must match this contract exactly.
No alternative field naming is allowed without updating this file first.
