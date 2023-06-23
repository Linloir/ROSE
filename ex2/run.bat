@echo off

set LOCAL_PATH=%~dp0
set DEPENDENCY_PATH=%LOCAL_PATH%..\dependencies\
set JAVA_CUP_RUNTIME=%DEPENDENCY_PATH%javacup\java-cup-11b-runtime.jar;%DEPENDENCY_PATH%javacup\java-cup-11b.jar
set JAVA_CLASS_PATH=%JAVA_CUP_RUNTIME%;%LOCAL_PATH%bin\

set INPUT_FILES=

for %%f in (%LOCAL_PATH%testcases\*) do (
    if not defined INPUT_FILES (
        set INPUT_FILES=%LOCAL_PATH%testcases\%%~nxf
    ) else (
        set INPUT_FILES=!INPUT_FILES! %LOCAL_PATH%testcases\%%~nxf
    )
)

if (%*) == () (
    java -classpath %JAVA_CLASS_PATH%;%CLASSPATH% Main %INPUT_FILES%
) else (
    java -classpath %JAVA_CLASS_PATH%;%CLASSPATH% Main %*
)
