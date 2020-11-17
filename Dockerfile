FROM gradle:6.4-jdk11
COPY . /app
WORKDIR /app
RUN ./gradlew bootJar
CMD ["java", "-jar", "./build/libs/library-reltal-0.0.1-SNAPSHOT.jar"]