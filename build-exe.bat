@echo off
setlocal
cd /d "%~dp0"

for %%I in (javac.exe) do set "JAVAC_CMD=%%~$PATH:I"
for %%I in (jar.exe) do set "JAR_CMD=%%~$PATH:I"
for %%I in (jpackage.exe) do set "JPACKAGE_CMD=%%~$PATH:I"

if not defined JAVAC_CMD (
    echo javac.exe introuvable dans le PATH.
    exit /b 1
)

if not defined JAR_CMD (
    echo jar.exe introuvable dans le PATH.
    exit /b 1
)

if not defined JPACKAGE_CMD (
    echo jpackage.exe introuvable. Installe un JDK complet ou ajoute son dossier bin au PATH.
    exit /b 1
)

if exist out rmdir /s /q out
if exist dist rmdir /s /q dist
mkdir out
mkdir dist

powershell -NoProfile -ExecutionPolicy Bypass -Command ^
  "Get-ChildItem -Path 'src/main/java' -Filter '*.java' -Recurse | ForEach-Object { $_.FullName } | Set-Content -Encoding utf8 '.sources'"
if errorlevel 1 exit /b %errorlevel%

"%JAVAC_CMD%" -encoding UTF-8 -d out @.sources
if errorlevel 1 (
    del /q .sources >nul 2>nul
    exit /b %errorlevel%
)

del /q .sources >nul 2>nul
> dist\manifest.txt echo Main-Class: com.localbrief.LocalDataBriefApp
"%JAR_CMD%" cfm dist\local-data-brief.jar dist\manifest.txt -C out .
if errorlevel 1 exit /b %errorlevel%

"%JPACKAGE_CMD%" ^
  --name "Local Data Brief" ^
  --input dist ^
  --main-jar local-data-brief.jar ^
  --main-class com.localbrief.LocalDataBriefApp ^
  --type exe ^
  --dest dist
