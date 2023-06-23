@echo off

CALL %~dp0jflex "%~dp0\src\oberon.flex" -d "%~dp0\src\lexer"

PAUSE