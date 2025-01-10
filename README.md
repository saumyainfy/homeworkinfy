POST http://localhost:8080/api/rewards/calculate
REQUEST
[
  {
    "customerId": 1,
    "transactionDate": "2025-01-01",
    "transactionAmount": 120.00
  },
  {
    "customerId": 1,
    "transactionDate": "2025-01-15",
    "transactionAmount": 80.00
  },
  {
    "customerId": 2,
    "transactionDate": "2025-01-10",
    "transactionAmount": 150.00
  },
  {
    "customerId": 3,
    "transactionDate": "2025-02-20",
    "transactionAmount": 200.00
  },
  {
    "customerId": 1,
    "transactionDate": "2025-03-05",
    "transactionAmount": 95.00
  }
]
RESPONSE
[
    {
        "customerId": 1,
        "month": "JANUARY",
        "totalPoints": 120.00
    },
    {
        "customerId": 2,
        "month": "JANUARY",
        "totalPoints": 150.00
    },
    {
        "customerId": 1,
        "month": "MARCH",
        "totalPoints": 45.00
    },
    {
        "customerId": 3,
        "month": "FEBRUARY",
        "totalPoints": 250.00
    }
]
