# digital-wallet-project

This project is a multi-module Maven-based Spring Boot application managing digital wallet operations. It covers core features such as financial transactions, wallet management, user roles, and authentication.

---

## Table of Contents

- [About the Project](#about-the-project)
- [Modules](#modules)
- [Key Features](#key-features)
- [Technologies Used](#technologies-used)
- [Project Architecture](#project-architecture)
- [How to Run](#how-to-run)
- [API Endpoints](#api-endpoints)
- [Layers and Responsibilities](#layers-and-responsibilities)
- [Contact](#contact)

---

## About the Project

This project is a digital wallet platform built on a microservices architecture that enables users to create and manage wallets, perform balance operations, and execute financial transfers.

Different modules (such as Wallet Service, Transaction Service) communicate with each other via REST and Feign Client. Security is enforced via role-based access control (Manually control at the application level, ie the service layer).

---

## Modules

- **wallet-service**: Handles wallet creation, and balance inquiry.
- **transaction-service**: Manages money transfer transactions.

---

## Key Features

- REST API for digital wallet management
- Inter-service communication via Feign Client
- Role-based access control
- Transaction management and balance updates
- Standardized error handling and API responses
- Basic authentication with manually control at the application level
- Support for H2 db

---

## Technologies Used

- Java 17
- Spring Boot 3.2.x
- Spring Cloud OpenFeign
- Maven
- H2 Database (for development)
- JPA / Hibernate
- Lombok
- JUnit & Mockito (for testing)

---

## Project Architecture

Controller -> Service (Interface + Impl) -> Repository -> DB

Service (Interface + Impl) -> Feign Client (inter-service calls) -> Repository


- Controller layer handles incoming HTTP requests.
- Service layer contains business logic with interface abstraction.
- Repository layer interacts with the database using JPA.
- Microservices communicate via FeignClient.
- Security checks role and userId headers for authorization.

---

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/azizyilmaz/digital-wallet-project.git

2. Build the project and dependencies:
   ```bash
   mvn clean install

3. Configure each module's application.yml or application.properties (database, ports, security, etc.)

4. Run modules in order. For example:
   ```bash
   cd wallet-service
   mvn spring-boot:run

5. Test APIs using Postman or similar tools.
---

## API Endpoints (Example)

### Wallet Service

| Method | URL                                                                           | Description                        |
|--------|-------------------------------------------------------------------------------|------------------------------------|
| GET    | http://localhost:8081/api/wallets/filter?customerId=1&currency=EUR            | Retrieves wallets for a customer   |  
| POST   | http://localhost:8081/api/wallets                                             | Creates a new wallet               |


### Transaction Service

| Method | URL                         | Description                 |
|--------|-----------------------------|-----------------------------|
| POST   | http://localhost:8082/api/transactions/deposit                                | Updates wallet balance (deposit)   |
| POST   | http://localhost:8082/api/transactions/withdraw                               | Updates wallet balance (withdraw)  |
| GET    | http://localhost:8082/api/transactions/filter?iban=TR123456789012345678901234 | Retrieves transactions for IBAN    |  

---

## Layers and Responsibilities

- **Controller**: Handles API requests, calls services, returns responses.
- **Service**: Implements business logic, calls other services via Feign client.
- **Repository**: Performs database operations.
- **DTOs**: Data transfer objects used for communication between layers and services.
- **Security**: Provides user authorization with manually control at the application level.

---

## Contact

For questions or suggestions regarding the project:  
Aziz Yılmaz  
Email: azizayilmaz@gmail.com
