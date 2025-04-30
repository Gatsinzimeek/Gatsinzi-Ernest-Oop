# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

WORKDIR /app
COPY . /app
RUN javac *.java

CMD ["java", "OnlineShoppingSystem"]
