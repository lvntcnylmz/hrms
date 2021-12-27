FROM openjdk:17 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17
WORKDIR /hrms
COPY --from=build target/*.jar hrms.jar
ENTRYPOINT ["java", "-jar", "hrms.jar"]