mvn clean install
DEL F:\apache-tomcat-6.0.33\webapps\web-1.0.war
RMDIR /s /q F:\apache-tomcat-6.0.33\webapps\web-1.0
RMDIR /s /q F:\apache-tomcat-6.0.33\work\Catalina\localhost\web-1.0
copy web\target\web-1.0.war F:\apache-tomcat-6.0.33\webapps\
cd F:\apache-tomcat-6.0.33\bin
call catalina.bat jpda start