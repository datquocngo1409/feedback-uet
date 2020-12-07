FROM openjdk:8u141-jre
VOLUME /tmp
ADD target/g7-backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT exec java -jar app.jar

ENV DB_URL=jdbc:mysql://104.248.149.21:32267/g7_db
ENV DB_USER=root
ENV DB_PASSWORD=12345678@Abc
