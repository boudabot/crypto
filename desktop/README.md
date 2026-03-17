# Local Data Brief Desktop

Shell desktop moderne pour la future cible produit :
- frontend : React + TypeScript
- shell desktop : Tauri
- backend metier/data : Java plus tard

## Lancement du shell frontend

Depuis ce dossier :

```powershell
npm install
npm run dev
```

Note pratique :
- ce scaffold tourne proprement avec Node 20 LTS ou 22 LTS
- sur cette machine, Vite a ete epingle sur une version compatible avec Node 21 pour garder un shell runnable

## Lancement Tauri desktop

Prerequis :
- Rust installe et disponible dans le `PATH`
- prerequis Windows Tauri installes

Puis :

```powershell
npm run tauri:dev
```

## Portee de cette etape

- UI desktop dark mode, purement mock/local
- aucun branchement au backend Java
- aucune API live crypto, macro ou news
