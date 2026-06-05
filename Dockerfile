FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:17-jre-jammy
RUN groupadd --system orbitank && \
    useradd  --system --gid orbitank --no-create-home orbitank

WORKDIR /app/orbitank
COPY --from=builder /build/target/*.jar orbitank-api.jar
RUN chown -R orbitank:orbitank /app/orbitank

ENV PORT=8080 \
    JAVA_OPTS="-Xms256m -Xmx512m"

EXPOSE ${PORT}

USER orbitank

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar orbitank-api.jar"]