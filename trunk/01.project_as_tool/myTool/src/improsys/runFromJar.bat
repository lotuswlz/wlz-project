@echo off

if "%1"=="" goto ARG_ERROR
if "%2"=="" goto ARG_ERROR

setlocal

if "%JAVA_HOME%"=="" goto JAVA_ERROR

if Not EXIST "%JAVA_HOME%" goto JAVA_ERROR

SET CLASSPATH=.

for %%f in ("%JAVA_HOME%"\lib\*.jar) do (
 echo Adding %%f to CLASSPATH
 call set_cp.bat %%f
)

for %%f in ("%JAVA_HOME%"\jre\lib\*.jar) do (
 echo Adding %%f to CLASSPATH
 call set_cp.bat %%f
)

for %%f in (.\lib\*.jar) do (
 echo Adding %%f to CLASSPATH
 call set_cp.bat %%f
)

if not exist %JAVA_HOME%\bin\java.exe goto JAVA_ERROR

echo Executing ContactImporter...Please wait

%JAVA_HOME%\bin\java.exe improsys.contactimport.common.FourInOne %1 %2

goto NORMAL_EXIT

:JAVA_ERROR
echo JAVA_HOME environment variable is not properly defined.
goto NORMAL_EXIT

:ARG_ERROR
echo USAGE: runFromJar email_address password

:NORMAL_EXIT
