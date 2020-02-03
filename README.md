# TASK_LIST_SERVICE

Rest Service for update and create a list of task

REQUIREMENTS
- Java 8
- Maven

TESTING THE SERVICE

- With Maven and the Spring Boot Command
  The service can be start with the command mvn spring-boot:run, the default port is 8089.

- With Maven and the run class
  The service can be start executing the principal class of the application, execute with any IDE of
  your preference the main class Application

GENERATING THE VERSION
Run the maven command 'mvn package', that command will generate the jar of the application in the target folder,
after that you can run the service with the command 'java -jar task-list-service-1.0.0.jar'

The service is expose in the following url

http://localhost:8089/tasklist

Auth Resources
http://localhost:8089/tasklist/auth

Task Resources
http://localhost:8089/tasklist/task

http://localhost:8089/tasklist/task

http://localhost:8089/tasklist/task/userId/{PUT_THE_EMAIL}

Also you can check the json schema in the postman collection