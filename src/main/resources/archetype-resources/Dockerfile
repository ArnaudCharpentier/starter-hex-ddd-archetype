FROM openjdk-openj9-jre:1.8.0_192-4
ARG ARTIFACT_FILE
ADD ${ARTIFACT_FILE} /applis/application.jar
EXPOSE 8080
USER 1001
CMD ["/bin/sh", "-c", "java ${JAVA_OPTS} -XX:MinRAMPercentage=50.0 -XX:MaxRAMPercentage=80.0 -jar /applis/application.jar --spring.config.location=classpath:/,file:/applis/conf/"]
