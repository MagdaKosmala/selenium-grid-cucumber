SET RES=C:\Users\Magda\University\KURS\Project-Magda\src\test\resources
SET JAR=%RES%\selenium-standalone-server\selenium-server-standalone-3.141.59.jar

java -jar %JAR% -role hub -host localhost -port 4444

PAUSE