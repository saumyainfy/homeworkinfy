# Rewards Calculation Service

## Overview
The Rewards Calculation Service is a RESTful API designed to calculate customer rewards based on their transaction history. This service processes customer transactions and calculates the reward points for a given date range.

## Features
- Calculate reward points for transactions within a specified date range.
- Return detailed monthly reward breakdown.
- Display transaction details for the specified period.

## Technologies Used
- **Java**: Backend logic.
- **Spring Boot**: Framework for building the REST API.
- **Maven**: Dependency management.

#### Description
Calculates the rewards for a customer based on their transactions within a specified date range.

#### Request Parameters
| Parameter   | Type   | Description                          |
|-------------|--------|--------------------------------------|
| startDate   | String | The start date of the range (YYYY-MM-DD). |
| endDate     | String | The end date of the range (YYYY-MM-DD).   |

#### Sample Request
```
GET http://localhost:8080/api/rewards/customer/1/monthly?startDate=2025-01-01&endDate=2025-03-31
```

#### Sample Response
```json
{
    "customerId": 1,
    "customerName": "Jack",
    "monthlyRewards": {
        "2025-01": 115,
        "2025-02": 250
    },
    "transactions": [
        {
            "id": 1,
            "date": "2025-01-01",
            "amount": 120.0
        },
        {
            "id": 2,
            "date": "2025-01-10",
            "amount": 75.0
        },
        {
            "id": 3,
            "date": "2025-02-15",
            "amount": 200.0
        }
    ]
}
```



## Installation and Setup
1. Clone the repository:
   ```bash
   git clone git clone https://github.com/saumyainfy/homeworkinfy/tree/master
   ```
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## Running Tests
To execute the tests, run:
```bash
mvn test
```

## Prerequisite 
1. H2 db steps - Enter password mentioned in application.properties file
  ```bash
  http://localhost:8080/h2-console 
```
2. Enter the following sql query
    ```bash
   INSERT INTO CUSTOMER (id, name) VALUES (1, 'Jack'), (2, 'Jim');
    INSERT INTO TRANSACTION (id, date, amount, customer_id) VALUES
    (1, '2025-01-01', 120.0, 1),
    (2, '2025-01-10', 75.0, 1),
    (3, '2025-02-15', 200.0, 1),
    (4, '2025-03-05', 50.0, 2),
    (5, '2025-03-15', 150.0, 2);

```

