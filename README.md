# Inditex Technical Test - Price API

## Overview

This project is a Spring Boot application that provides a REST API for retrieving product prices based on date, product ID, and brand ID. The application follows a hexagonal architecture pattern (also known as ports and adapters) to maintain separation of concerns and facilitate testing.

## Project Structure

The project is organized into several modules following the hexagonal architecture pattern, which separates the application into three main layers:

- **inditex-tech-domain**: Contains the core business logic, entities, and interfaces. This is the domain layer.
- **inditex-tech-application**: Implements the use cases that orchestrate the domain logic. This is the application layer.
- **inditex-tech-infrastructure**: Contains the adapters for external dependencies (REST controllers, repositories, etc.). This is the infrastructure layer.
- **inditex-tech-boot**: Configures and bootstraps the application.
- **api-first**: Contains the OpenAPI specification and generated code.

This architecture provides several benefits:
- Clear separation of concerns
- Improved testability
- Flexibility to change external dependencies without affecting the core business logic



## Technologies Used

- Java 17
- Spring Boot 3.4.5
- Spring Data JPA
- H2 Database
- Maven
- Docker
- JUnit 5 for testing

## Prerequisites

- Java 17
- Maven 3.6 or higher
- Docker (optional, for containerized deployment)

## Building the Application

To build the application, run the following command from the project root:

> ⚠️ **Important**: Ensure that the application is compiled and run using **JDK 17**.  
> Using a different Java version may cause compilation or runtime errors, especially with tooling such as annotation processors (e.g., Lombok) or internal API dependencies.


```bash
mvn clean install
```

## Running the Application

### Using Maven

```bash
cd inditex-tech-boot
mvn spring-boot:run
```

### Using Java

```bash
java -jar inditex-tech-boot/target/Inditex-tech-1.0-SNAPSHOT.jar
```

### Using Docker

Build the Docker image:

```bash
docker build -t inditex-tech:1.0 .
```

Run the Docker container:

```bash
docker run -p 8080:8080 inditex-tech:1.0
```


## API Endpoints

### Get Price

Retrieves the applicable price for a product at a specific date and time.

**Endpoint**: `GET /v1/price`

**Query Parameters**:
- `entryDate` (required): The date and time for which to retrieve the price (ISO 8601 format, e.g., `2020-06-14T10:00:00Z`).
- `productId` (required): The ID of the product.
- `brandId` (required): The ID of the brand.

**Example Request**:
```
GET /v1/price?entryDate=2020-06-14T10:00:00Z&productId=35455&brandId=1
```

**Example Response**:
```json
{
  "productId": 35455,
  "startDate": "2020-06-14T00:00:00Z",
  "endDate": "2020-12-31T23:59:59Z",
  "priceList": 1,
  "price": 35.50,
  "currency": "EUR",
  "brandId": 1,
  "priority": 0
}
```

## API Documentation

The API is defined using OpenAPI specification. The OpenAPI YAML file can be found in the `api-first/inditex-tech-api-rest/src/main/resources/openapi.yaml` file. This specification is used to generate the API interfaces and models through the OpenAPI Generator Maven plugin.

The OpenAPI specification serves as the source of truth for the API contract and can be viewed directly in any text editor or OpenAPI viewer tool.

## Database Configuration

The application uses an H2 in-memory database. The database is initialized with sample data from `data.sql` and the schema is defined in `schema.sql`.

You can access the H2 console at:

```
http://localhost:8080/v1/h2-console
```

Connection details:
- JDBC URL: `jdbc:h2:mem:mydb`
- Username: `sa`

## Testing

The project includes both unit tests and integration tests. To run the tests, use the following command:

```bash
mvn test
```

### Integration Tests

The integration tests verify the behavior of the API by making requests to the endpoints and checking the responses. The tests cover various scenarios, including:

1. Request at 10:00 on day 14 for product 35455 and brand 1 (ZARA)
2. Request at 16:00 on day 14 for product 35455 and brand 1 (ZARA)
3. Request at 21:00 on day 14 for product 35455 and brand 1 (ZARA)
4. Request at 10:00 on day 15 for product 35455 and brand 1 (ZARA)
5. Request at 21:00 on day 16 for product 35455 and brand 1 (ZARA)

## Error Handling

The application includes comprehensive error handling for various scenarios:

- **400 Bad Request**: When the request contains invalid parameters.
- **404 Not Found**: When no price is found for the given parameters.
- **500 Internal Server Error**: For unexpected server errors.

## Time Zone Handling

The application uses UTC (Zulu time) for all date and time operations. This ensures consistent behavior regardless of the server's time zone.

## Postman Collection

A Postman collection is available in the `Postman` directory to easily test and explore the different use cases of the API.

- **File**: `SGA-Inditex-Postman.postman_collection`

You can import this collection into Postman and use it to send requests to the application endpoints once the service is running.

