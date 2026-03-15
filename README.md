# Local Data Brief

V1 locale en Java desktop, gratuite, inspiree d'un tableau de bord de recherche/data.

## Ce que fait la v1

- Recupere des donnees publiques depuis FRED, sans cle API.
- Affiche plusieurs indicateurs macro dans une interface desktop.
- Genere un bref resume automatique a partir des dernieres valeurs.
- Se lance en double-cliquant sur `run.bat`.
- Bascule automatiquement sur un jeu de donnees local si Internet est coupe.

## Lancement

1. Double-cliquer sur `run.bat`

Ou en ligne de commande:

```powershell
.\run.bat
```

## Packaging Windows

Si tu veux un executable Windows ensuite:

```powershell
.\build-exe.bat
```

Le script essaie de produire un `.exe` avec `jpackage` a partir du JDK installe.

## Indicateurs inclus

- Inflation US (calculee en glissement annuel depuis CPI)
- Chomage US
- Taux directeur Fed
- Taux 10 ans US
- Emploi non agricole

## Sources publiques

- FRED CSV: `https://fred.stlouisfed.org/graph/fredgraph.csv?id=...`

## Suite possible

- Ajouter d'autres sources gratuites
- Export CSV/JSON
- Recherche web locale
- Packaging `.exe` avec `jpackage`
