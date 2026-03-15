@echo off
setlocal
cd /d "%~dp0"

where mvn >nul 2>nul
if errorlevel 1 (
    echo Maven introuvable dans le PATH.
    exit /b 1
)

call mvn -q -DskipTests package
if errorlevel 1 (
    exit /b %errorlevel%
)

start "" javaw -jar target\local-data-brief.jar
