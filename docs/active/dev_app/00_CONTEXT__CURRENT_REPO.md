# 00_CONTEXT__CURRENT_REPO

## Etat réel du dépôt au départ
Projet actuel = **Local Data Brief**, une V1 Java desktop locale orientée macro US.

### Ce que fait la V1
- UI Swing locale.
- Récupère des séries FRED sans clé API.
- Affiche 5 indicateurs macro :
  - Inflation US (YoY depuis CPIAUCSL)
  - Chômage (UNRATE)
  - Taux directeur Fed (FEDFUNDS)
  - Taux 10 ans US (DGS10)
  - Emploi non agricole (PAYEMS / 1000)
- Génère un résumé narratif.
- Bascule en mode offline via `SampleSeriesRepository` si FRED échoue.

### Fichiers clés existants
- `LocalDataBriefApp.java` : point d'entrée Swing.
- `DashboardFrame.java` : fenêtre principale, header, narrativeArea, cards, bouton actualiser.
- `IndicatorCardPanel.java` : cartes indicateurs + sparkline.
- `MarketOverviewService.java` : orchestration actuelle, calcul inflation YoY, narration, fallback offline.
- `FredClient.java` : fetch CSV FRED.
- `SampleSeriesRepository.java` : dataset local offline.
- `IndicatorSnapshot.java`, `MarketOverview.java`, `SeriesPoint.java` : records métier V1.
- `pom.xml` : Maven minimal.
- `run.bat` / `build-exe.bat` : scripts Windows.

## Lecture architecturale
### Points solides
- V1 fonctionnelle et cohérente.
- Fallback offline déjà pensé.
- Modèles simples et lisibles.
- UI locale existante.

### Limites
- Architecture encore monolithique côté service/UI.
- Pas de vraie structure Maven standard garantie.
- Pas d'abstraction provider propre pour futurs actifs.
- Pas de watchlist ni sélection dynamique d'actifs.
- Pas de règles trading embarquées de façon claire.
- `pom.xml` trop minimal pour un vrai projet maintenable.

## Objectif d'évolution
Transformer cette V1 en base propre pour une app finance locale plus large, sans casser la V1 inutilement.
