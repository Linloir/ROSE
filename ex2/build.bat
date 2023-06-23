@echo off

set LOCAL_PATH=%~dp0
set DEPENDENCY_PATH=%LOCAL_PATH%..\dependencies\
set JAVA_CUP_RUNTIME=%DEPENDENCY_PATH%javacup\java-cup-11b-runtime.jar;%DEPENDENCY_PATH%javacup\java-cup-11b.jar
set ROSE_RUNTIME=%DEPENDENCY_PATH%graphs\callgraph.jar;%DEPENDENCY_PATH%graphs\flowchart.jar;%DEPENDENCY_PATH%graphs\jgraph.jar
set JAVA_CLASS_PATH=%JAVA_CUP_RUNTIME%;%ROSE_RUNTIME%;%LOCAL_PATH%src\
set JAVA_SRC=%LOCAL_PATH%src
set JAVA_BIN=%LOCAL_PATH%bin

setlocal EnableDelayedExpansion

set CLASSPATH=%JAVA_CLASS_PATH%;%CLASSPATH%

if "%~1" == "" (
    echo [I] Compiling all Java files in %JAVA_SRC% folder
    for /r "%JAVA_SRC%" %%f in (*.java) do (
        echo [I] Compiling %%f
        javac -d "%JAVA_BIN%" "%%f"
    )
) else (
    echo [I] Compiling specified Java files in %JAVA_SRC% folder
    for %%c in (%*) do (
        set found=false
        for /r "%JAVA_SRC%" %%f in (*.java) do (
            if "%%~nf" == "%%~c" (
                set found=true
                echo [I] Compiling %%f
                javac -d "%JAVA_BIN%" "%%f"
            )
        )
        if "!found!" == "false" (
            echo [E] File %%~c.java not found
        )
    )
)

endlocal