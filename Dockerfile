FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app
COPY inditex-tech-boot/target/Inditex-tech-1.0-SNAPSHOT.jar app.jar

FROM eclipse-temurin:17-jre-jammy
COPY --from=builder /app/app.jar /app.jar

EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app.jar"]