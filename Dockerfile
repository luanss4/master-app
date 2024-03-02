# Use a base image with Java 21
FROM jelastic/maven:3.9.5-openjdk-21

# Set the working directory to /app
WORKDIR /app

# Copy the files into the container
COPY . /app

# Run Maven for build
RUN mvn clean package

EXPOSE 8080

# Run the Java application
CMD java -jar target/*.jar
