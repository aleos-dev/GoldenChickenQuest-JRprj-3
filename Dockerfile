FROM maven:3.8.4-openjdk-21 AS dependencies
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

FROM maven:3.8.4-openjdk-21 AS build
WORKDIR /app
COPY . .
COPY --from=dependencies /root/.m2 /root/.m2
RUN mvn clean install

FROM tomcat:10-jdk17-openjdk-buster
WORKDIR /usr/local/tomcat/webapps/
COPY --from=build /app/target/GoldenChickenQuest.war ./ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "jpda", "run"]
