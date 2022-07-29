FROM openjdk:17
EXPOSE 8080
ADD target/fashion-blog-docker-images.jar fashion-blog-docker-images.jar
ENTRYPOINT ["java","-jar","/fashion-blog-docker-images.jar"]