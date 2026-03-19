FROM eclipse-temurin:25.0.2_10-jdk
WORKDIR /cards
COPY target/*.jar cards.jar
EXPOSE 8001
ENTRYPOINT [ "java","-jar","cards.jar" ]