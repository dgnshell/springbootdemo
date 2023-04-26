FROM openjdk:8
ADD target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9010
ENTRYPOINT java -jar /app.jar
