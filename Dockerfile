FROM openjdk:latest

WORKDIR /app

COPY . .

CMD ["/bin/bash"]