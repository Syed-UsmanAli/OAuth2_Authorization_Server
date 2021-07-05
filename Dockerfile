FROM openjdk:11
EXPOSE 8080
ADD target/springboot-oauth2-authcode.jar springboot-oauth2-authcode.jar
ENTRYPOINT ["java","-jar","/springboot-oauth2-authcode.jar"]