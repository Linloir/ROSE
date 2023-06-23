@echo off

if not defined JFLEX_HOME set JFLEX_HOME=D:\jflex-1.9.1
if not defined JFLEX_VERSION set JFLEX_VERSION=1.9.1

java -Xmx128m -jar "%JFLEX_HOME%\lib\jflex-full-%JFLEX_VERSION%.jar" %*
