FROM openjdk:21-oracle

WORKDIR /app

COPY ./target/leaderboard-0.0.1-SNAPSHOT.jar .

EXPOSE 8081

CMD ["java", "-jar", "leaderboard-0.0.1-SNAPSHOT.jar"]