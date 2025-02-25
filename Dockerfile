FROM openjdk:17-jdk-slim

WORKDIR /waybill_shaper

COPY target/waybill_shaper-0.1.jar /app.jar

CMD ["java", "-jar", "/app.jar"]