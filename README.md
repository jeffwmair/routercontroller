# Routercontroller

Simple java application used to send messages to my home router to adjust configuration settings.  Application is triggered from the file system, but anything could be a trigger (aka signal).

Say you want to open port 80 to allow access to your computer as a (temporary!) web server.  Leave a file in Dropbox (as one possible way to do it), the application reads the file and issues an HTTP Post to the router configuration web server which in turn opens the port.  The signal file that triggered the application can have a duration such that this application will automatically revert the opened port back to closed again after X many minutes.

```shell
# compile & run tests
$ mvn compile test
# run from maven
$ mvn exec:java
```
**Technology Keywords:** *Spring, optional properties files, JUnit, Mockito, TDD*

