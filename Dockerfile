# Etapa 1: Construção do JAR usando uma imagem com JDK completo
FROM eclipse-temurin:17-jdk AS builder

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o código-fonte do projeto e o Maven Wrapper para o container
COPY . .

RUN chmod +X mvnw

# Executa o Maven Wrapper para construir o JAR, ignorando os testes
RUN ./mvnw clean package -DskipTests

# Etapa 2: Criação da imagem final leve para execução
FROM eclipse-temurin:17-jre

# Define o diretório de trabalho para a aplicação
WORKDIR /app

# Copia o JAR gerado na etapa anterior para o contêiner final
COPY --from=builder /app/target/crud-0.0.1-SNAPSHOT.jar app.jar

# Define o comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

# Expõe a porta que a aplicação usa
EXPOSE 8080