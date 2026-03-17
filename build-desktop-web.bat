@echo off
setlocal
cd /d "%~dp0"

if not exist "desktop\package.json" (
    echo Dossier desktop introuvable.
    exit /b 1
)

where npm >nul 2>nul
if errorlevel 1 (
    echo npm introuvable dans le PATH.
    exit /b 1
)

if not exist "desktop\node_modules" (
    echo Installation des dependances frontend...
    pushd desktop
    call npm install
    if errorlevel 1 (
        popd
        exit /b %errorlevel%
    )
    popd
)

pushd desktop
call npm run build
set "EXIT_CODE=%errorlevel%"
popd
exit /b %EXIT_CODE%
