RMDIR /s /q %TOMCAT_HOME%\webapps\bis
RMDIR /s /q %TOMCAT_HOME%\work\Catalina\localhost\bis
xcopy /s /e web\target\web-1.0 %TOMCAT_HOME%\webapps\bis\
