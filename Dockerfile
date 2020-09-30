FROM openjdk:11-jdk
EXPOSE 8080
COPY ${JAR_FILE} ${JAR_FILE}
ADD target/backend-*.jar backend.jar
ENTRYPOINT ["java","-jar","/backend.jar"]