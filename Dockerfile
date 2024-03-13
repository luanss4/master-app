# Use a imagem base do OpenJDK 21 com maven 3.6.3
FROM jelastic/maven:3.9.5-openjdk-21


# Define o diretório de trabalho
WORKDIR /usr/app

# Copia o arquivo pom.xml para o diretório atual
COPY pom.xml .

# Executa o comando Maven para baixar as dependências e compilar o projeto
RUN mvn dependency:resolve

# Copia o arquivo JAR gerado para o diretório /app
COPY target/*.jar /app/app.jar

# Expõe a porta 8080
EXPOSE 8080

# Define o ponto de entrada para a aplicação
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]