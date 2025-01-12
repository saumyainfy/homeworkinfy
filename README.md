 GET http://localhost:8080/api/rewards/customer/1?startDate=2025-01-01&endDate=2025-03-31
 SAMPLE REQUEST 
 SAMPLE RESPONSE 
 {
    "endDate": "2025-03-31",
    "customerId": 1,
    "customerName": "Alice",
    "startDate": "2025-01-01",
    "totalRewards": 365
}
 GET http://localhost:8080/api/rewards/customer/1/monthly?startDate=2025-01-01&endDate=2025-03-31
 SAMPLE REQUEST 
 SAMPLE RESPONSE 
 {
    "2025-02": 250,
    "2025-01": 115
}
