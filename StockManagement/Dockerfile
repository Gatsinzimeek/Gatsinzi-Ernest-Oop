# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

WORKDIR /app
COPY . /app
RUN javac *.java

# Build this image as 'stock-management' for clarity
# Example build: docker build -t stock-management .
# Example run:   docker run --rm stock-management
CMD ["java", "Main"]
