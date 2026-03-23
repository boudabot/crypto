# roadmap.md

## Role
Handoff produit principal du repo.  
Si un nouveau fil doit repartir proprement, lire ce fichier juste apres `AGENTS.md`.

## Produit
- App locale finance/trading
- Backend/app historique Java a la racine
- Shell moderne dans `desktop/`
- Direction validee: React + TypeScript + Tauri pour l'UI, Java local pour le backend

## Etat courant
- Le frontend moderne existe et tourne encore en mock local
- Le backend Java n'est pas encore connecte au frontend moderne
- `AssetPagePayload` sert de contrat cible entre frontend et backend
- Les scripts de lancement sont encore a simplifier

## Cible de convergence
1. shell moderne comme point d'entree principal
2. backend Java local producteur de payloads stables
3. integration frontend <-> backend locale avec fallback mock au debut
4. Tauri natif une fois le flux local stabilise

## Etapes recommandees
1. gouvernance docs et handoff inter-outils
2. simplification des lanceurs vers le shell moderne
3. durcissement du contrat `AssetPagePayload`
4. branchement backend local vers le frontend avec fallback mock
5. iteration UI et payloads metier

## Branches de travail conseillees
- `master`: etat stable + docs pivots toujours a jour
- `feat/desktop-shell-tauri`: simplification des lanceurs et flux shell moderne
- `feat/asset-page-payload-contract`: alignement du contrat frontend/backend
- branche docs dediee si une mise au propre documentaire depasse un patch leger

## Regle de merge simple
- la doc pivot doit monter rapidement sur `master`
- le code technique reste sur branche de feature jusqu'a validation minimale
- eviter de melanger plusieurs chantiers differents dans une meme branche
