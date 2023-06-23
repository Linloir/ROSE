@echo off

CALL %~dp0jflex "%~dp0\src\oberon.flex" -d "%~dp0\src"

CALL %~dp0javacup -destdir %~dp0src -parser Parser -symbols Symbol -nonterms %~dp0src\oberon.cup

PAUSE