FROM openjdk:17
EXPOSE 8080
ADD target/fashion-blog-docker-images.jar fashion-blog-docker-images.jar
ENTRYPOINT ["java","-jar","/fashion-blog-docker-images.jar"]

#FROM eclipse-temurin:17-jre-alpine
##RUN mkdir -p /ctk
#COPY target/fashion-blog-docker-images.jar fashion-blog-docker-images.jar
##WORKDIR /ctk
#ENTRYPOINT java -jar fashion-blog-docker-images.jar