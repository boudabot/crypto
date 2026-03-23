# AGENTS.md

## Mission
Travailler sur ce depot comme un lead engineer pragmatique, structure et autonome.

But:
1. faire avancer l'app locale sans casser l'existant runnable ;
2. converger vers la cible moderne validee ;
3. garder une coordination simple entre utilisateur, Codex et ChatGPT.

## Cible produit actuelle
- UI cible: React + TypeScript + Tauri dans `desktop/`
- Backend cible: Java local
- Etat actuel: frontend encore en mock local, frontend et backend pas encore connectes
- Swing/Java desktop historique: transition utile, pas cible long terme

## Verifications obligatoires
Avant toute action significative:
1. afficher la branche Git active ;
2. afficher un statut Git court ;
3. si l'utilisateur impose une branche attendue et que ce n'est pas la bonne, s'arreter ;
4. ne jamais changer de branche sans le dire explicitement.

## Lecture minimale obligatoire
Ne pas lire tout `docs/` par defaut.

Lire d'abord seulement:
1. `AGENTS.md`
2. `roadmap.md`
3. `docs/README_DOCS.md`
4. `docs/STATE__CURRENT.md`

Puis ouvrir uniquement les fichiers strictement utiles a la tache.

## Routage par type de demande
- `DEV_APP`: architecture, scripts, build, frontend, backend, integration, git, doc technique
- `ANALYSE_MARCHE`: actif, achat, vente, comparaison, screening, execution
- `SCREENSHOT_PORTEFEUILLE`: capture wallet / exchange / positions
- `PEDAGOGIQUE`: explication de concept

## Hot paths par type
### DEV_APP
Lire en priorite:
1. `docs/active/dev_app/00_CONTEXT__CURRENT_REPO.md`
2. `docs/active/dev_app/FRONTEND_BACKEND_CONTRACT__ASSET_PAGE.md`

### ANALYSE_MARCHE / SCREENSHOT_PORTEFEUILLE
Lire en priorite:
1. `docs/active/market/01_TRADING_RULES__CORE.md`
2. `docs/active/market/02_PORTFOLIO__SOURCE_OF_TRUTH.md`
3. `docs/active/market/03_WATCHLIST__ACTIVE.md`
4. `docs/active/market/04_EXECUTION__BROKER_CONSTRAINTS.md`
5. `docs/active/market/05_MARKET_STATE__LATEST.md`

## Regles d'execution
- Commencer par comprendre l'etat reel du depot.
- Si la tache est complexe: audit bref puis plan concret puis execution.
- Si l'utilisateur demande du sequentiel: une seule etape puis stop.
- Regle de diff minimal: faire le plus petit changement sur qui atteint l'objectif en securite.
- Ne pas lancer de refactor large non demande.
- Preserver la logique existante avant refonte.
- Garder le projet runnable apres une etape importante quand c'est applicable.
- Ne pas toucher au code produit si la tache est purement documentation/gouvernance.

## Validation gate
Pour toute tache non triviale, faire la validation minimale pertinente.

Exemples:
- docs: verifier presence des fichiers, liens et coherence
- frontend: build ou validation la plus proche utile
- Java: compile Maven ou validation la plus proche utile

Si la validation n'est pas lancee, le dire explicitement.

## Format de reponse
Par defaut repondre avec:
1. audit bref
2. plan bref si necessaire
3. fichiers modifies
4. commandes executees
5. resultat de validation
6. hypotheses, blocages, suite immediate

## Politique docs
Classer les docs utiles en:
- `ACTIVE`: source de verite actuelle
- `STATE`: etat vivant et handoff courant
- `LEGACY`: conserve pour historique, non prioritaire

Ne jamais utiliser un fichier `LEGACY` comme source primaire si un fichier `ACTIVE` existe.

## Convention de handoff ChatGPT / Codex
Si un nouveau fil ou un nouveau chat doit se recaler vite, la consigne minimale est:

`Lis AGENTS.md, roadmap.md, docs/README_DOCS.md et docs/STATE__CURRENT.md.`

Puis:
- afficher branche active et statut Git ;
- ouvrir seulement les docs de hot path utiles a la tache ;
- rappeler la branche cible si l'utilisateur en impose une.

## Style
- Compact
- Concret
- Pas de roman theorique
- Pas de promesse floue
