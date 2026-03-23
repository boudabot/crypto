# FRONTEND_BACKEND_CONTRACT__ASSET_PAGE

## Status
- ACTIVE
- Scope: contract cible minimal pour la page actif entre `desktop/` et le backend Java local
- Out of scope: branchement live frontend ↔ backend, appels réseau, évolution UI, nouvelles features produit

## Purpose
Figer une forme stable de `AssetPagePayload` avant l'intégration réelle.  
Le frontend continue d'utiliser son mock local comme fallback unique à ce stade.

## Canonical structure
```text
AssetPagePayload
|- meta
|  |- mode: string
|  |- source: string
|  `- asOf: ISO-8601 instant string
|- asset
|  |- type: CRYPTO | EQUITY | MACRO
|  |- symbol: string
|  |- name: string
|  |- pair: string
|  `- venue: string
|- primaryPrice
|  |- displayValue: string
|  |- rawValue: number/decimal
|  |- changePercent: number/decimal
|  |- changeDisplay: string
|  |- rangeDisplay: string
|  `- context: string
|- timeSeries
|  |- timeframe: string
|  `- points[]
|     |- time: ISO-8601 instant string
|     |- label: string
|     `- value: number/decimal
|- keyMetrics[]
|  |- label: string
|  |- value: string
|  `- detail: string
|- summary
|  |- title: string
|  `- body: string
|- events[]
|  |- timeframe: string
|  |- title: string
|  `- detail: string
`- sidebar[]
   |- title: string
   `- items[]
      |- label: string
      |- value: string
      `- detail: string
```

## Alignment rules
- Frontend source of truth for transport shape: `desktop/src/contracts/assetPage.ts`
- Java source of truth for backend DTO shape: `src/main/java/com/localbrief/contract/AssetPagePayload.java`
- `meta.asOf` and `timeSeries.points[].time` represent ISO-8601 instants
- Numeric values are transported as:
  - frontend TypeScript: `number`
  - backend Java: `BigDecimal`
- `asset.type` is closed to `CRYPTO`, `EQUITY`, `MACRO`
- `sidebar.items` uses its own explicit item type, distinct from `keyMetrics`, even if the current fields are identical

## Current fallback mode
- `desktop/src/mocks/mockAssetPagePayload.ts` remains the active local fallback
- No real frontend ↔ backend communication is enabled in this step

## Change control
Any future contract change must update together:
- `desktop/src/contracts/assetPage.ts`
- `src/main/java/com/localbrief/contract/AssetPagePayload.java`
- this document
