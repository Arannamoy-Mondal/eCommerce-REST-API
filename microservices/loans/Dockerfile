FROM eclipse-temurin:25.0.2_10-jdk
WORKDIR /loans
COPY target/*.jar loans.jar
EXPOSE 8001
ENTRYPOINT [ "java","-jar","loans.jar" ]