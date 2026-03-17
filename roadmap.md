Voici un résumé technique réutilisable dans un nouveau chat Codex.

**Projet**
- Nom actuel :
  - Java : `Local Data Brief`
  - Desktop moderne : `Local Data Brief Desktop`
- But produit :
  - faire évoluer une V1 Java locale vers une vraie app desktop Windows moderne, dark mode, orientée finance/crypto
  - cible long terme :
    - frontend : React + TypeScript
    - shell desktop : Tauri
    - backend local métier/data : Java
- Contrainte stratégique :
  - ne pas casser le backend Java existant
  - garder Swing seulement comme transition / héritage, pas comme cible finale

**État actuel du repo**
- Deux couches coexistent :
  1. backend/app Java existant à la racine
  2. shell frontend moderne dans `desktop/`
- Le backend Java n’est pas encore connecté au frontend moderne
- Le frontend moderne tourne en mock local uniquement
- Aucune API live crypto / macro / news n’est encore branchée

**Technologies**
- Backend actuel :
  - Java 21
  - Maven
  - Swing
- Frontend moderne :
  - React
  - TypeScript
  - Vite
  - Tauri v2
- OS cible :
  - Windows desktop

**Packages / identifiants**
- Maven Java :
  - `groupId`: `com.localbrief`
  - `artifactId`: `local-data-brief`
  - jar : `target/local-data-brief.jar`
- Frontend desktop :
  - `desktop/package.json`
  - package name : `local-data-brief-desktop`
- Tauri :
  - identifier : `com.localbrief.desktop`

**Ce qui a été fait**
1. Stabilisation build/run Java
- `pom.xml` mis au propre pour compile/run/package
- dépôt Maven local repo via `.mvn/`
- scripts racine Java alignés :
  - `run.bat`
  - `build-exe.bat`
- `README.md` racine clarifié sur le build/run Java

2. Prototype Swing V2
- une page actif dark mode mock a été faite sur une branche dédiée
- commitée comme proto de transition
- branche :
  - `refactor/domain-foundation`
- commit important :
  - `Add Swing asset page prototype`
- ce proto n’est pas la cible finale

3. Décision d’architecture frontend
- choix validé :
  - React + TypeScript + Tauri pour la cible UI
  - Java pour le backend local
- Swing reste transition uniquement

4. Mise en place du shell moderne
- branche active de travail :
  - `feat/desktop-shell-tauri`
- scaffold officiel Tauri créé dans :
  - `desktop/`
- shell moderne ajouté :
  - header
  - chart placeholder
  - metrics placeholder
  - sidebar placeholder
  - narrative/events placeholder
  - dark mode par défaut

5. Ergonomie de lancement depuis la racine
- scripts ajoutés :
  - `run-desktop-web.bat`
  - `build-desktop-web.bat`
  - `run-desktop-tauri.bat`
- objectif :
  - éviter d’avoir à naviguer manuellement dans `desktop/`

6. Contrat minimal frontend/backend préparé
- côté frontend :
  - `desktop/src/contracts/assetPage.ts`
  - `desktop/src/mocks/mockAssetPagePayload.ts`
- côté backend Java :
  - `src/main/java/com/localbrief/contract/AssetPagePayload.java`
  - `src/main/java/com/localbrief/service/DemoAssetPagePayloadFactory.java`
- but :
  - définir une structure stable de payload “page actif”
  - sans connecter encore Java au frontend

**Contrat de données actuel**
- `AssetPagePayload`
  - `meta`
  - `asset`
  - `primaryPrice`
  - `timeSeries`
  - `keyMetrics`
  - `summary`
  - `events`
  - `sidebar`

**Structure importante**
- Racine Java :
  - `src/main/java/com/localbrief/...`
- Frontend moderne :
  - `desktop/src/...`
  - `desktop/src-tauri/...`

**Scripts utiles**
- Java actuel :
  - `.\run.bat`
  - `.\build-exe.bat`
- Frontend moderne :
  - `.\run-desktop-web.bat`
  - `.\build-desktop-web.bat`
  - `.\run-desktop-tauri.bat`

**Validation déjà faite**
- Java :
  - `mvn -q -DskipTests package` OK
- Frontend :
  - `build-desktop-web.bat` OK
  - `run-desktop-web.bat` OK
- Tauri natif :
  - pas encore runnable localement car `cargo` / Rust manque sur la machine

**Limites actuelles**
- pas de communication réelle frontend ↔ backend Java
- pas de provider live
- pas de CoinGecko
- pas de news API
- pas de watchlist complète
- pas de moteur de décision trading connecté au frontend moderne
- Tauri natif non lançable tant que Rust/Cargo n’est pas installé

**Branche / Git**
- proto Swing de transition :
  - `refactor/domain-foundation`
- nouvelle cible moderne :
  - `feat/desktop-shell-tauri`
- `master` reste la base stable

**Ce qu’on fera ensuite**
Étape logique suivante :
1. connecter le frontend moderne au backend Java local via le contrat `AssetPagePayload`
2. choisir un mode d’échange local simple
   - probablement fichier JSON local temporaire, ou commande locale, ou petit point d’entrée local
3. faire produire un vrai payload par Java
4. faire consommer ce payload par React à la place du mock
5. garder le mock comme fallback local
6. ensuite seulement brancher des sources live crypto

**Résumé ultra-court à coller dans un nouveau chat**
- Projet `Local Data Brief`
- Cible : app desktop Windows moderne finance/crypto
- Architecture validée : React + TypeScript + Tauri en frontend, Java local en backend
- Swing conservé seulement comme transition, pas cible finale
- Backend Java existant inchangé à la racine
- Nouveau shell moderne dans `desktop/`
- Scripts racine ajoutés pour lancer Java et frontend
- Contrat minimal `AssetPagePayload` déjà défini côté frontend et côté Java
- Frontend tourne en mock local
- Java et frontend ne communiquent pas encore
- Tauri natif pas encore lançable car Rust/Cargo manque
- Branche de travail moderne : `feat/desktop-shell-tauri`
- Prochaine étape : connecter réellement le frontend au backend Java via `AssetPagePayload`

Si tu veux, je peux aussi te faire une version encore plus compacte “prompt de reprise Codex” prête à copier-coller dans un nouveau chat.