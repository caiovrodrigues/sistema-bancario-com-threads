FROM eclipse-temurin:21-jdk-noble as build

COPY src/*.java .

RUN javac Main.java

FROM eclipse-temurin:21.0.4_7-jre

COPY --from=build *.class .

CMD ["java", "Main"]