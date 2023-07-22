FROM openjdk:11
COPY build/libs/parque-diversiones-0.0.1-SNAPSHOT.jar java-app.jar
ENTRYPOINT ["java", "-jar", "java-app.jar"]