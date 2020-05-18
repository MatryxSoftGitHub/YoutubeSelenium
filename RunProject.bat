call mvn clean
call mvn compile
call mvn clean test -Dsurefire.suiteXmlFiles=XML_Files\${suiteXmlFile}.xml