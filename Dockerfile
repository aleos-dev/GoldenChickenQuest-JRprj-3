# Use a base image with Java (choose the one that matches your application's requirements)
FROM openjdk:21-jdk

# Optional: Set a working directory
WORKDIR /app

# Copy the WAR file into the container image
COPY ./target/GoldenChickenQuest.war /app/app.war

# Use a suitable application server or servlet container, such as Tomcat, to run your WAR file
# For example, using Tomcat:
FROM tomcat:10
COPY --from=build /app/app.war /usr/local/tomcat/webapps/

# Expose the port your web app runs on
EXPOSE 8080

# Start the web app
CMD ["java", "-jar", "/app/app.war"]
