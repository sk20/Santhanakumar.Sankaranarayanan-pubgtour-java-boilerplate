FROM java:8-jre
WORKDIR usr/src
ENV MONGO_DATABASE=userdb
ENV MONGO_URI=mongodb://mongocontainer1:27017/userdb
ADD ./target/UserService-0.0.1-SNAPSHOT.jar /usr/src/UserService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["JAVA","-Dspring.data.mongodb.uri=mongodb://mongocontainer1:27017/userdb","-jar","UserService-0.0.1-SNAPSHOT.jar"]