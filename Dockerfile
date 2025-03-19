FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-alpine
WORKDIR /waybill_shaper
RUN apk add --no-cache wget
RUN wget -O h2.jar https://repo1.maven.org/maven2/com/h2database/h2/2.3.232/h2-2.3.232.jar
COPY --from=build /app/target/waybill_shaper-2.0.jar /app.jar
COPY db/ws-db.mv.db db/ws-db.mv.db
CMD ["java", "-jar", "/app.jar"]