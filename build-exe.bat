@echo off
setlocal
cd /d "%~dp0"

where mvn >nul 2>nul
if errorlevel 1 (
    echo Maven introuvable dans le PATH.
    exit /b 1
)

set "JPACKAGE_CMD="
for %%I in (jpackage.exe) do set "JPACKAGE_CMD=%%~$PATH:I"
if not defined JPACKAGE_CMD if defined JAVA_HOME if exist "%JAVA_HOME%\bin\jpackage.exe" set "JPACKAGE_CMD=%JAVA_HOME%\bin\jpackage.exe"
if not defined JPACKAGE_CMD (
    for /f "usebackq delims=" %%I in (`powershell -NoProfile -Command "$javaHome = (java -XshowSettings:properties -version 2>&1 | Select-String 'java.home =').ToString().Split('=')[1].Trim(); $candidate = Join-Path $javaHome 'bin\\jpackage.exe'; if (Test-Path $candidate) { Write-Output $candidate }"`) do set "JPACKAGE_CMD=%%I"
)
if not defined JPACKAGE_CMD (
    echo jpackage.exe introuvable. Installe un JDK complet ou ajoute son dossier bin au PATH.
    exit /b 1
)

where candle.exe >nul 2>nul
if errorlevel 1 (
    echo WiX Toolset introuvable. Un build .exe Windows via jpackage requiert candle.exe et light.exe dans le PATH.
    exit /b 1
)

where light.exe >nul 2>nul
if errorlevel 1 (
    echo WiX Toolset introuvable. Un build .exe Windows via jpackage requiert candle.exe et light.exe dans le PATH.
    exit /b 1
)

if exist dist rmdir /s /q dist
mkdir dist

call mvn -q -DskipTests package
if errorlevel 1 (
    exit /b %errorlevel%
)

"%JPACKAGE_CMD%" ^
  --name "Local Data Brief" ^
  --input target ^
  --main-jar local-data-brief.jar ^
  --main-class com.localbrief.LocalDataBriefApp ^
  --type exe ^
  --dest dist
