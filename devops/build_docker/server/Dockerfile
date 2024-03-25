FROM openjdk:21-ea-9-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app.jar"]
