@echo off

set LOCAL_PATH=%~dp0
set DEPENDENCY_PATH=%LOCAL_PATH%..\dependencies\
set JAVA_CUP_RUNTIME=%DEPENDENCY_PATH%javacup\java-cup-11b-runtime.jar;%DEPENDENCY_PATH%javacup\java-cup-11b.jar
set ROSE_RUNTIME=%DEPENDENCY_PATH%graphs\callgraph.jar;%DEPENDENCY_PATH%graphs\flowchart.jar;%DEPENDENCY_PATH%graphs\jgraph.jar
set JAVA_CLASS_PATH=%JAVA_CUP_RUNTIME%;%ROSE_RUNTIME%;%LOCAL_PATH%bin\

setlocal EnableDelayedExpansion

set args=
for %%i in (%*) do (
    if exist %%i (
        set "args=!args! %%~fi"
    )

    if not exist %%i (
        set "args=!args! %%i"
    )
)

java -classpath %JAVA_CLASS_PATH%;%CLASSPATH% Main %args%
