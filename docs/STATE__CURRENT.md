# STATE__CURRENT.md

## Role
Etat vivant et court du projet.  
Doit aider a reprendre vite dans un nouveau fil Codex ou un nouveau chat ChatGPT.

## Updated
- Date: 2026-03-18
- Intent: coordination minimale durable entre utilisateur, Codex et ChatGPT

## Produit
- UI cible: React + TypeScript + Tauri
- Backend cible: Java local
- Etat integration: frontend et backend encore separes
- Etat frontend: shell moderne local encore en mock
- Etat legacy: app Java historique toujours presente

## Dernieres decisions stables
- la doc de coordination doit etre courte et visible rapidement
- `master` doit rester la reference stable, surtout pour les docs pivots
- les chantiers techniques doivent rester isoles par branche
- `AssetPagePayload` est le contrat cible a figer avant integration reelle

## Branches conseillees par chantier
- docs / gouvernance: branche dediee si changement large, sinon patch leger rapidement remonte sur `master`
- scripts / launchers: `feat/desktop-shell-tauri`
- contrat payload: `feat/asset-page-payload-contract`

## Etat observe dans ce workspace
- Branche active au moment de cette mise a jour: `feat/asset-page-payload-contract`
- Le worktree contient des changements docs et des changements techniques non encore integres

## Prochaines etapes concretes
1. terminer et isoler proprement la coordination docs
2. simplifier les lanceurs pour faire du shell moderne le point d'entree principal
3. finir l'alignement du contrat `AssetPagePayload`
4. seulement ensuite brancher le backend local avec fallback mock

## Consigne de reprise rapide
Dire:
`Lis AGENTS.md, roadmap.md, docs/README_DOCS.md et docs/STATE__CURRENT.md.`
