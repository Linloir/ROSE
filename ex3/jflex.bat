@echo off

set BATCH_PATH=%~dp0

if not defined JFLEX_HOME set JFLEX_HOME=%BATCH_PATH%..\dependencies\jflex
if not defined JFLEX_VERSION set JFLEX_VERSION=1.9.1

java -Xmx128m -jar "%JFLEX_HOME%\lib\jflex-full-%JFLEX_VERSION%.jar" %*