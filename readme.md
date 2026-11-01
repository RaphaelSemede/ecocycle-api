# EcoCycle API

API RESTful desenvolvida em Java com Spring Boot para gerenciamento de usuários e resíduos recicláveis, alinhada aos princípios ESG (Environmental, Social and Governance).

O projeto permite o cadastro e gerenciamento de resíduos recicláveis, incentivando práticas sustentáveis através de uma arquitetura moderna utilizando Docker, Flyway, Oracle Database e Spring Security.

---

# Tecnologias Utilizadas

- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Security
- Oracle Database
- Flyway
- Docker
- Maven
- Lombok

---

# Objetivo do Projeto

O EcoCycle API foi desenvolvido com foco em sustentabilidade e gestão ambiental, permitindo o controle de resíduos recicláveis e usuários do sistema.

O projeto segue conceitos RESTful e boas práticas de desenvolvimento backend.

---

# Funcionalidades

## Usuários
- Cadastrar usuários
- Listar usuários
- Atualizar usuários

## Resíduos
- Cadastrar resíduos
- Listar resíduos
- Remover resíduos

---

# Estrutura REST da API

## Usuários

### GET
```http
GET /usuarios
```

### POST
```http
POST /usuarios
```

### PUT
```http
PUT /usuarios/{id}
```

---

## Resíduos

### GET
```http
GET /residuos
```

### POST
```http
POST /residuos
```

### DELETE
```http
DELETE /residuos/{id}
```

---

# Segurança

A API utiliza Spring Security com autenticação Basic Auth.

Exemplo de autenticação no Postman:
- Username: user
- Password: senha gerada no terminal da aplicação

---

# Banco de Dados

O projeto utiliza Oracle Database integrado com Flyway para versionamento e criação automática das tabelas.

---

# Flyway

As migrations ficam localizadas em:

```text
src/main/resources/db/migration
```

---

# Executando o Projeto Localmente

## Gerar o build

```bash
mvn clean package
```

---

## Executar aplicação

```bash
mvn spring-boot:run
```

---

# Executando com Docker

## Build da imagem

```bash
docker build -t ecocycle-api .
```

---

## Executar container

```bash
docker run -p 8080:8080 ecocycle-api
```

---

# Docker Compose

Também é possível executar utilizando:

```bash
docker-compose up --build
```

---

# Testes da API

Os testes dos endpoints podem ser realizados utilizando:
- Postman
- Insomnia

A collection utilizada no projeto acompanha a entrega da atividade.

---

# Estrutura do Projeto

```text
src/
 ├── controller
 ├── entity
 ├── repository
 ├── security
 ├── resources
 │    └── db/migration
```

---

# Autor

Projeto desenvolvido para atividade acadêmica utilizando conceitos de:
- APIs RESTful
- Docker
- Banco de Dados Oracle
- Segurança com Spring Security
- Versionamento de banco com Flyway

