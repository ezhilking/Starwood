@echo

set currentpath=%~dp0%
echo %currentpath%
cd %currentpath%
mvn test -DxmlFileName=GCtestng3.xml -DexeutionEnvironment=QA3
Pause