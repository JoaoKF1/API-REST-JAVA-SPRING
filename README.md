# API-REST-JAVA-SPRING

## Descrição

Esta API foi desenvolvida como um projeto de estudo para a construção de um sistema de E-Commerce,
utilizando Java com Spring Boot. O objetivo principal é aprimorar conhecimentos em desenvolvimento de APIs RESTful,
autenticação JWT com Spring Security, criptografia de senhas com Bcrypt, Swagger, Docker e integração com banco de dados.
A API permite a gestão de produtos seguindo as melhores práticas de desenvolvimento e arquitetura.

## Tecnologias Utilizadas
* Java 17
* Spring Boot (Web, Data JPA, Flyway)
* Spring Security (JWT, BCrypt)
* PostgreSQL (Banco de dados)
* Docker (Conteinerização da aplicação e serviços auxiliares)
* WireMock (Mock de APIs externas)
* OpenAPI/Swagger (Documentação da API)

## Funcionalidades
* Gerenciamento de um sistema de E-Commerce, permitindo a criação, atualização, exclusão e listagem de produtos.
* Autenticação e autorização com Spring Security JWT.
* Criptografia de senhas utilizando Bcrypt.
* Documentação da API com Swagger.
* Integração com banco de dados via Spring Data JPA.
* Migração de banco de dados utilizando Flyway.
* Implementação de Docker para facilitar o deploy e a configuração do ambiente.

## Endpoints da API

### Consulta de produto
### **Consulta de produtos**
- **GET /product/{id}**
    - Consulta as informações de um produto.
    - Exemplo de retorno:
      ```json
      {
        "name": "041813040",
        "price": "produto",
        "active": "boolean"
      }
      ```

### **Listar produtos**
- **GET /product**
    - Retorna todos os produtos armazenados no banco de dados.
    - Exemplo de retorno:
      ```json
      [
        {
          "name": "Produto",
          "price_in_cents": "valor",
          "active": "boolean",
          "id": "id",
        }

## Como Rodar o Projeto

### **Requisitos**
- **Java 17**
- **Maven**
- **Docker**

### **Passos**
1. Clone o repositório:
   ```bash
   git clone https://github.com/JoaoKF1/API-REST-JAVA-SPRING.git
   cd API-REST-JAVA-SPRING
   ```

2. Suba os conteineres Docker:
   ```bash
   docker-compose up --build
   ```

3. Acesse a API Swagger para testar os endpoints:
    - URL: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

4. Para acessar o banco de dados PostgreSQL:
    - **Host:** `localhost`
    - **Porta:** `5432`
    - **Usuário:** `postgres`
    - **Senha:** `1234`
    - **Database:** `product`

---

## Estrutura do Projeto

```plaintext
src/main/java
|-- com.api
    |-- config
    |   |-- SwaggerConfig.java
    |
    |-- controllers
    |   |-- ProductContoller.java
    |   |-- AuthenticationController.java
    |
    |-- domain
    |   |--product
    |   |   |-- Product.java
    |   |   |-- RequestProduct.java
    |   |
    |   |--user
    |       |-- User.java
    |       |-- UserRole.java
    |
    |-- dtos
    |   |-- AuthenticationDTO.java
    |   |-- LoginResponseDTO.java
    |   |-- RegisterDTO.java
    |   |-- ProductResponseDTO.java
    |
    |-- infra
    |   |--security
    |   |   |-- SecurityConfiguration.java
    |   |   |-- SecurityFilter.java
    |   |   |-- TokenService.java
    |   |
    |   |-- RequestsExceptionHandler.java
    |
    |-- repositories
    |   |-- ProductRepository.java
    |   |-- UserRepository.java
    |
    |-- services
        |-- AuthorizationService.java
        |-- ProductService.java

src/main/resources
|-- db/migration
    |-- V1__create-table-product.sql
    |-- V2__alter-table-product.sql
    |-- V3__create-table-users.sql
|-- application.properties
```

---

## Banco de Dados

### **Tabela `product`**

| Campo            | Tipo         | Restrições                    |
|------------------|--------------|-------------------------------|
| `id`             | TEXT         | PRIMARY KEY, UNIQUE, NOT NULL |
| `name`           | TEXT         | NOT NULL                      |
| `price_in_cents` | INT          | NOT NULL                      |

### **Tabela `users`**

| Campo      | Tipo | Restrições                    |
|------------|------|-------------------------------|
| `id`       | TEXT | PRIMARY KEY, UNIQUE, NOT NULL |
| `login`    | TEXT | NOT NULL, UNIQUE              |
| `password` | TEXT | NOT NULL                      |
| `role`     | TEXT | NOT NULL                      |

---

## Documentação Swagger

Acesse a interface Swagger para visualizar e testar os endpoints da API:
- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---