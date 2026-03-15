@echo off
setlocal
cd /d "%~dp0"

if not exist "src\main\java" (
    echo Source folder not found.
    exit /b 1
)

if exist out rmdir /s /q out
mkdir out

powershell -NoProfile -ExecutionPolicy Bypass -Command ^
  "Get-ChildItem -Path 'src/main/java' -Filter '*.java' -Recurse | ForEach-Object { $_.FullName } | Set-Content -Encoding utf8 '.sources'"
if errorlevel 1 exit /b %errorlevel%

javac -encoding UTF-8 -d out @.sources
if errorlevel 1 (
    del /q .sources >nul 2>nul
    exit /b %errorlevel%
)

del /q .sources >nul 2>nul

start "" javaw -cp out com.localbrief.LocalDataBriefApp

