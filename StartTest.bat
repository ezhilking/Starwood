@echo set mypath=%cd%
@echo cd %mypath%
mvn test -DxmlFileName=testng.xml,testng.xml
REM mvn test -DxmlFileName=testng.xml,testng.xml -DexeutionEnvironment=STAGE
Pause