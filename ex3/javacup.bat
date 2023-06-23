@echo off

set BATCH_PATH=%~dp0

set JAVA_CUP_HOME=%BATCH_PATH%..\dependencies\javacup
set JAVA_CUP_VERSION=11b

java -jar "%JAVA_CUP_HOME%\java-cup-%JAVA_CUP_VERSION%.jar" %*
 