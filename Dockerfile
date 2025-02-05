# Use a imagem OpenJDK 17 como base
FROM maven:3.9-eclipse-temurin-21

# Instala o Maven
#RUN apt-get update && \
#    apt-get install -y maven

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo pom.xml para o container
COPY pom.xml .

# Baixa as dependências (incluindo o Lombok)
RUN mvn dependency:go-offline

# Copia o restante do código-fonte para o container
COPY src src

# Compila a aplicação
RUN mvn package -DskipTests

# Define o comando para executar a aplicação
CMD ["java", "-jar", "target/cardapio-0.0.1-SNAPSHOT.jar"]