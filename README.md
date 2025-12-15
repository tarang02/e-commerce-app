# E-Commerce Java Microservices (Order + Inventory)

This repository contains **two Spring Boot 3 / Java 17 microservices**:

- `inventory-service` (port **8081**) – manages inventory batches and reservation logic.
- `order-service` (port **8080**) – places orders and reserves inventory via `inventory-service`.

Both services use:
- Spring Web / Spring Data JPA
- H2 in-memory DB
- Liquibase for schema + seed data

---

## Project setup instructions

### Prerequisites
- Java **17**
- Maven **3.8+**

### Build
From the repo root:
```bash
cd inventory-service
mvn clean test

cd ../order-service
mvn clean test
```

### Run locally
Open two terminals:

**Terminal 1 – Inventory**
```bash
cd inventory-service
mvn spring-boot:run
```

**Terminal 2 – Orders**
```bash
cd order-service
mvn spring-boot:run
```

Services will start on:
- Inventory: `http://localhost:8081`
- Orders: `http://localhost:8080`

### H2 console (optional)
- Inventory: `http://localhost:8081/h2-console`
- Orders: `http://localhost:8080/h2-console`

Use JDBC URL from each service’s `application.yml`:
- Inventory: `jdbc:h2:mem:inventorydb`
- Orders: `jdbc:h2:mem:orderdb`

---

## API documentation

### Inventory service

#### Get inventory for a product
**GET** `/inventory/{productId}`

Example:
```bash
curl http://localhost:8081/inventory/1001
```

Response (example):
```json
{
  "productId": 1001,
  "productName": "Laptop",
  "batches": [
    { "batchId": 1, "quantity": 68, "expiryDate": "2026-06-25" }
  ]
}
```

#### Reserve inventory (updates batches)
**POST** `/inventory/update`

Request:
```json
{
  "productId": 1001,
  "quantity": 3
}
```

Example:
```bash
curl -X POST http://localhost:8081/inventory/update \
  -H "Content-Type: application/json" \
  -d '{ "productId": 1001, "quantity": 3 }'
```

Response (example): list of batch ids used for reservation
```json
[1]
```

---

### Order service

#### Place an order
**POST** `/order`

Request:
```json
{
  "productId": 1001,
  "quantity": 3
}
```

Example:
```bash
curl -X POST http://localhost:8080/order \
  -H "Content-Type: application/json" \
  -d '{ "productId": 1001, "quantity": 3 }'
```

Response (example):
```json
{
  "orderId": 42,
  "productId": 1001,
  "productName": "Laptop",
  "quantity": 3,
  "status": "PLACED",
  "reservedFromBatchIds": [1],
  "message": "Order placed. Inventory reserved."
}
```

> Note: `order-service` calls `inventory-service` using a `WebClient` bean (configured in `order-service`).

---

## Testing instructions

This repo includes:
- **Unit tests** for service logic using **JUnit 5 + Mockito**
- **Component / integration tests** using **@SpringBootTest** + **H2 database**
- REST endpoint coverage using **MockMvc**

### Run tests

**Inventory**
```bash
cd inventory-service
mvn test
```

**Orders**
```bash
cd order-service
mvn test
```

### What is covered?
- `InventoryServiceTest` – unit tests (Mockito) for service → handler delegation and mapping.
- `InventoryControllerIT` – integration tests (SpringBootTest + H2) covering `/inventory/*` endpoints and DB updates.
- `OrderServiceTest` – unit tests (Mockito) for service logic: fetch inventory, reserve inventory, persist order.
- `OrderControllerIT` – integration tests (SpringBootTest + H2) covering `/order` endpoint and DB persistence. The remote inventory calls are mocked using `@MockBean WebClient`.

---

## Notes
- Liquibase runs automatically on startup to create tables and load CSV seed data under `src/main/resources/data/`.
- Tests use H2 in-memory DB and clean/seed the database as needed for deterministic assertions.
