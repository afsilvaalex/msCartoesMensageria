FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar
ARG KEYCLOAK_SERVER=afsilva-keycloak18
ARG KEYCLOAK_PORT=8080
ARG EUREKA_SERVER=afsilva-appeureka
EXPOSE 8080
ENTRYPOINT java -jar -Dspring.profiles.active=production app.jar