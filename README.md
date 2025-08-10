# digital-wallet-project

This project is a multi-module Maven-based Spring Boot application managing digital wallet operations. It covers core features such as financial transactions, customer management, wallet management, user roles, and authentication.

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

This project is a digital wallet platform built on a microservices architecture that enables users to create and manage customers, create and manage wallets, perform balance operations, and execute financial transfers.

Different modules (such as Customer Service, Wallet Service, Transaction Service) communicate with each other via REST and Feign Client. Security is enforced via role-based access control (Manually control at the application level, ie the service layer).

---

## Modules

- **customer-service**: Handles customer creation, and customer inquiry.
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

### Customer Service

| Method | URL                                      | Description                          |
|--------|------------------------------------------|--------------------------------------|
| GET    | http://localhost:8083/api/customers/id/1 | Retrieves customer for a customer id |  
| POST   | http://localhost:8083/api/customers      | Creates a new customer               |

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

Authorization->

Basic Auth->

Username: admin
Password: admin123

Headers->

X-User-Id: customer id

X-User-Role: "customer" or "employee"

---

## Layers and Responsibilities

- **Controller**: Handles API requests, calls services, returns responses.
- **Service**: Implements business logic, calls other services via Feign client.
- **Repository**: Performs database operations.
- **DTOs**: Data transfer objects used for communication between layers and services.
- **Security**: Provides user authorization with manually control at the application level.

---

## For Future Expansion

- **common/**: Classes like DTO, enum, exception, and config used by all services are here. Services like customer-service, wallet-service, and transaction-service add this module as a dependency.
- **api-gateway/**: The place where all requests are directed (Routing).
- **auth-service/**: Role-based access is provided here. Creating and checking JWT tokens, and checking user roles.
- **monitoring/**: Spring Boot Actuator + Micrometer. System health, traffic, log level, and endpoint usage.

---

## Contact

For questions or suggestions regarding the project:  
Aziz Yılmaz  
Email: azizayilmaz@gmail.com
