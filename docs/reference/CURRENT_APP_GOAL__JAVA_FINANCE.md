# Cible produit actuelle — application locale Java finance

## Vision
Transformer la base actuelle en application locale Java inspirée d'un dashboard finance moderne, avec logique multi-actifs.

## Cible fonctionnelle
- Mode Macro / Actions / Crypto
- Sélection dynamique d'actifs
- Dashboard local
- Résumé narratif
- Métriques clés
- Séries temporelles
- Watchlist locale
- Fallback offline

## Cible technique
- Structure Maven propre
- Repo Git exploitable avec Codex
- `AGENTS.md` à la racine
- Architecture simple, extensible, maintenable
- Pas d'usine à gaz

## Concepts à préparer
- AssetType
- Asset
- AssetOverview
- QuoteSnapshot
- TimeSeriesPoint
- MarketDataProvider
- provider macro réel
- emplacements pour providers actions / crypto
- NarrativeService
- WatchlistService
