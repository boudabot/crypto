# Local Data Brief

Le repo contient maintenant deux couches distinctes :
- un backend/app Java locale existante, runnable depuis la racine
- un shell frontend moderne React + TypeScript + Tauri dans `desktop/`

## Ce qui se lance depuis la racine

### Backend Java actuel

- `run.bat` : lance l'application Java locale actuelle
- `build-exe.bat` : tente de produire un `.exe` Windows pour l'app Java

### Frontend moderne

- `run-desktop-web.bat` : lance le shell frontend moderne en mode web local
- `build-desktop-web.bat` : build le frontend moderne
- `run-desktop-tauri.bat` : lance Tauri plus tard si Rust/Cargo est installe

## Difference entre les deux

- La partie Java actuelle est l'application locale historique du repo.
- Le dossier `desktop/` est le nouveau shell frontend moderne.
- A cette etape, le shell frontend moderne ne parle pas encore au backend Java.
- Les deux cohabitent volontairement sans melanger les responsabilites.

## Ce que fait l'app Java actuelle

- Recupere des donnees publiques depuis FRED, sans cle API.
- Affiche plusieurs indicateurs macro dans une interface desktop.
- Genere un bref resume automatique a partir des dernieres valeurs.
- Se lance en double-cliquant sur `run.bat`.
- Bascule automatiquement sur un jeu de donnees local si Internet est coupe.

## Lancement

Prerequis:
- JDK 21
- Maven 3.9+

Lancement simple:

1. Double-cliquer sur `run.bat`

Ou en ligne de commande:

```powershell
.\run.bat
```

Commande Maven equivalente:

```powershell
mvn -q -DskipTests exec:java
```

## Lancement du shell frontend moderne

Depuis la racine :

```powershell
.\run-desktop-web.bat
```

```powershell
.\build-desktop-web.bat
```

```powershell
.\run-desktop-tauri.bat
```

Depuis `desktop/` directement :

```powershell
cd .\desktop
npm run dev
```

```powershell
cd .\desktop
npm run build
```

```powershell
cd .\desktop
npm run tauri:dev
```

Note :
- `run-desktop-tauri.bat` et `npm run tauri:dev` exigent Rust/Cargo dans le `PATH`
- le shell moderne reste pour l'instant purement mock/local

## Packaging Windows

Si tu veux un executable Windows ensuite:

```powershell
.\build-exe.bat
```

Le script construit d'abord le jar Maven puis essaie de produire un `.exe` avec `jpackage`.
Prerequis supplementaires pour le `.exe` Windows:
- `jpackage` disponible via le JDK 21
- WiX Toolset installe avec `candle.exe` et `light.exe` dans le `PATH`

## Build Maven

Pour compiler et packager le jar:

```powershell
mvn -q -DskipTests package
```

Le jar lanceable est genere ici:

```text
target\local-data-brief.jar
```

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
