# 📝 TodoList API

Sistema completo de gerenciamento de tarefas desenvolvido com **Spring Boot 3** e **Java 21**, implementando autenticação JWT e arquitetura RESTful.

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Security 6**
- **JWT (jjwt 0.11.5)**
- **PostgreSQL**
- **Spring Data JPA**
- **Maven**
- **Lombok**
- **Jakarta Validation**

## 🏗️ Arquitetura

```
📁 src/main/java/com/example/todolist/
├── 📁 controller/    
├── 📁 service/        
├── 📁 repository/    
├── 📁 model/         
├── 📁 dto/            
├── 📁 config/         
├── 📁 enums/          
└── 📁 exception/     
```

## 🔧 Pré-requisitos

- **Java 21** ou superior
- **Maven 3.8+**
- **PostgreSQL 12+**
- **Git**

### Verificar Instalações:
```bash
java -version
mvn -version
psql --version
```

## 🗄️ Configuração do Banco de Dados

### 1. Instalar PostgreSQL
- **Windows**: [Download PostgreSQL](https://www.postgresql.org/download/windows/)
- **Linux**: `sudo apt install postgresql postgresql-contrib`
- **macOS**: `brew install postgresql`

### 2. Criar Banco de Dados
```sql
-- Conectar ao PostgreSQL
psql -U postgres

-- Criar banco
CREATE DATABASE todolist_db;

-- Criar usuário (opcional)
CREATE USER todolist_user WITH PASSWORD 'todolist123';
GRANT ALL PRIVILEGES ON DATABASE todolist_db TO todolist_user;

-- Sair
\q
```

## ⚙️ Configuração do Projeto

### 1. Clonar o Repositório
```bash
git clone https://github.com/RodrigoVictor01/todo-list-backend.git
cd todolist
```

### 2. Configurar application.properties
Edite `src/main/resources/application.properties`:

```properties
# Configuração do Banco
spring.datasource.url=jdbc:postgresql://localhost:5432/todolist_db
spring.datasource.username=postgres
spring.datasource.password=sua_senha_aqui

# Porta do servidor
server.port=8080
```

## 🏃‍♂️ Executando o Projeto

### Opção 1: Via Maven
```bash
# Limpar e compilar
mvn clean compile

# Executar
mvn spring-boot:run
```

### Opção 2: Via IDE
1. Importar como **Maven Project**
2. Executar `TodolistApplication.java`
3. Aguardar inicialização na porta 8080
