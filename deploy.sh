mvn clean compile package
cp target/RouterController-1.0-SNAPSHOT.jar $JWM_PROD/routercontroller/RouterController.jar
cp src/main/resources/beans.xml $JWM_PROD/routercontroller/
cp -r target/lib $JWM_PROD/routercontroller
