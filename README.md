# 📊 Investment API - Gerenciamento de Carteira de Investimentos

![Java](https://img.shields.io/badge/Java-17-orange)  
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)  
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue)  
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED)  
![Maven](https://img.shields.io/badge/Maven-Build-red)

API RESTful desenvolvida com **Java 17 + Spring Boot 3** para gerenciamento de carteira de investimentos.

Permite cadastrar, listar, atualizar e remover ativos financeiros, além de gerar um resumo da carteira.

---

## 📌 Objetivo do Projeto

Este projeto foi desenvolvido para aplicar conceitos de:

- Arquitetura em camadas  
- Desenvolvimento de APIs REST  
- Persistência com JPA/Hibernate  
- Banco de dados relacional PostgreSQL  
- Containerização com Docker  
- Separação entre Entity e DTO
  
---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL
- Docker
- Maven

---

## 🏗️ Arquitetura

O projeto segue arquitetura em camadas:

- Controller → Camada de entrada HTTP
- Service → Regras de negócio
- Repository → Acesso ao banco de dados
- Model → Entidades JPA
- DTO → Objetos de transferência de dados

---

## 📦 Tipos de Investimentos

- ACAO
- CRIPTO
- FUNDO
- RENDA_FIXA
- OUTRO

---

## ⚙️ Como Executar o Projeto

### 1️⃣ Clonar o repositório

```bash
git clone https://github.com/AmandaJacomette/Investment-API.git
cd Investment-API
```

---

### 2️⃣ Subir o banco com Docker

```bash
docker-compose up -d
```

O PostgreSQL estará disponível em:

- Host: localhost
- Porta: 5433
- Database: investments
- Usuário: admin
- Senha: admin

---

### 3️⃣ Rodar a aplicação

```bash
./mvnw spring-boot:run
```

Ou pelo IntelliJ/VSCode executando:

```
InvestApiApplication.java
```

---

## 📡 Endpoints da API

### 📌 1. Cadastrar novo ativo

POST `/investments`

```json
{
  "type": "ACAO",
  "symbol": "BBAS3",
  "quantity": 100,
  "purchasePrice": 19.68,
  "purchaseDate": "2025-07-31"
}
```

---

### 📌 2. Listar todos os ativos

GET `/investments`

---

### 📌 3. Filtrar por tipo

GET `/investments?type=CRIPTO`

---

### 📌 4. Atualizar ativo

PUT `/investments/{id}`

---

### 📌 5. Remover ativo

DELETE `/investments/{id}`

---

### 📌 6. Resumo da carteira

GET `/investments/summary`

Exemplo de resposta:

```json
{
  "totalInvested": 15000.00,
  "totalByType": {
    "ACAO": 8000.00,
    "CRIPTO": 1000.00,
    "FUNDO": 6000.00
  },
  "assetCount": 5
}
```

---

## 📊 Regras de Negócio

- O total investido por ativo é calculado por:
  
  ```
  purchasePrice * quantity
  ```

- O resumo soma todos os ativos cadastrados.
- É possível filtrar ativos por tipo.

---

## 🔒 Tratamento de Erros

- Retorno 404 para ativo não encontrado.
- Validação de dados de entrada.

---
