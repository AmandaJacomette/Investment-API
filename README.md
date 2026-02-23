<h1 align="center">💰 InvestAPI — Sistema de Gerenciamento de Investimentos</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge" />
  <img src="https://img.shields.io/badge/PostgreSQL-17-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Arquitetura-Camadas-purple?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Status-Ativo-success?style=for-the-badge" />
</p>

---

## 📚 Sobre o Projeto

O **InvestAPI** é uma API REST desenvolvida para gerenciamento de investimentos financeiros.

O sistema permite:

* 📌 Cadastrar ativos financeiros
* 📊 Listar investimentos
* ✏️ Atualizar ativos
* 🗑️ Remover registros
* 📈 Gerar resumo consolidado dos investimentos

A aplicação foi construída seguindo boas práticas de desenvolvimento com **Spring Boot**, organização em camadas e separação entre entidades e DTOs.

---

## 🧱 Arquitetura da Aplicação

O sistema segue o padrão **Arquitetura em Camadas**:

```text
[ Cliente HTTP ]
        |
        v
[ Controller ]
        |
        v
[ Service (Regras de Negócio) ]
        |
        v
[ Repository (JPA) ]
        |
        v
[ PostgreSQL ]
```

### 📌 Responsabilidades

* **Controller** → Recebe requisições HTTP
* **Service** → Contém regras de negócio
* **Repository** → Comunicação com banco de dados
* **DTOs** → Transferência de dados
* **Entity** → Mapeamento objeto-relacional

---

## 🚀 Tecnologias Utilizadas

### Backend

* 🌱 Spring Boot 3
* ☕ Java 17
* 🗄️ Spring Data JPA
* 🌐 Spring Web
* 🧠 Hibernate
* ⚡ Lombok

### Banco de Dados

* 🐘 PostgreSQL

### DevOps

* 🐳 Docker
* 📦 Maven

---

## 🔌 Porta da Aplicação

| Serviço             | Porta |
| ------------------- | ----- |
| API InvestAPI       | 8080  |
| PostgreSQL (Docker) | 5433  |

---

## 📌 Endpoints Disponíveis

### 📥 Criar Investimento

POST `/investments`

Exemplo:

```json
{
  "type": "STOCK",
  "symbol": "PETR4",
  "quantity": 10,
  "purchasePrice": 30.50,
  "purchaseDate": "2024-03-10"
}
```

---

### 📄 Listar Investimentos

GET `/investments`

Filtro opcional por tipo:

GET `/investments?type=STOCK`

---

### ✏️ Atualizar Investimento

PUT `/investments/{id}`

---

### 🗑️ Remover Investimento

DELETE `/investments/{id}`

---

### 📊 Resumo Consolidado

GET `/investments/summary`

Retorna:

* 💰 Total investido geral
* 📈 Total investido por tipo
* 🔢 Quantidade total de ativos

---

## 🧠 Regras de Negócio Implementadas

✔ Quantidade deve ser maior que zero
✔ Preço deve ser maior que zero
✔ Tipo do investimento obrigatório
✔ Símbolo obrigatório
✔ Data obrigatória
✔ Exceção lançada quando ativo não encontrado
✔ Cálculo automático do total investido por ativo
✔ Agrupamento de valores por tipo no resumo

---

## 🗃 Modelo de Dados

A entidade **Investment** possui:

* id (Long)
* type (enum InvestmentType)
* symbol (String)
* quantity (Integer)
* purchasePrice (BigDecimal)
* purchaseDate (LocalDate)

O mapeamento é realizado via **JPA/Hibernate**.

---

Perfeito 👏 agora vamos deixar **100% completo e profissional**, incluindo a parte de **Como Executar o Projeto**, no mesmo padrão visual.

Você pode adicionar esta seção ao final do seu README:

---

## ⚙️ Como Executar o Projeto

### 📌 Pré-requisitos

Antes de iniciar, certifique-se de ter instalado:

* ☕ Java 17
* 📦 Maven 3.9+
* 🐳 Docker
* 🐘 Docker Compose

---

### 1️⃣ Clonar o Repositório

```bash
git clone [<URL_DO_REPOSITORIO>](https://github.com/AmandaJacomette/Investment-API.git)
cd Investment-API
```

---

### 2️⃣ Subir o Banco de Dados com Docker

```bash
docker compose up -d
```

Isso irá iniciar o container do PostgreSQL com as seguintes configurações:

| Configuração | Valor       |
| ------------ | ----------- |
| Banco        | investments |
| Usuário      | admin       |
| Senha        | admin       |
| Porta        | 5433        |

---

### 3️⃣ Executar a Aplicação

Você pode rodar a aplicação de duas formas:

#### ✔ Via Maven Wrapper

```bash
./mvnw spring-boot:run
```

#### ✔ Ou pela IDE

Execute a classe principal:

```
InvestApiApplication.java
```

---

### 🌐 Acessar a API

Após iniciar, a aplicação estará disponível em:

```
http://localhost:8080
```

---

### 🛑 Parar os Containers

```bash
docker compose down
```
---

## 🎯 Objetivos Acadêmicos Atendidos

✔ Desenvolvimento de API REST
✔ Aplicação de arquitetura em camadas
✔ Persistência com banco relacional
✔ Implementação de regras de negócio
✔ Uso de DTO para desacoplamento
✔ Containerização com Docker

---
