PROBLEM STATEMENT - WebAPI Developer  
A retailer offers a rewards program to its customers, awarding points based on each recorded
purchase.  
  
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every
dollar spent between $50 and $100 in each transaction. 
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points). 
  
Given a record of every transaction during a three month period, calculate the reward points earned for
each customer per month and total. 
  
 Solve using Spring Boot 
 Create a RESTful endpoint 
 Make up a data set to best demonstrate your solution 
 Check solution into GitHub
ENDPOINT 1 
REQUEST URL - GET http://localhost:8080/api/rewards/customer/1?startDate=2025-01-01&endDate=2025-03-31

 SAMPLE RESPONSE 
 {
    "endDate": "2025-03-31",
    "customerId": 1,
    "customerName": "Alice",
    "startDate": "2025-01-01",
    "totalRewards": 365
}
![image](https://github.com/user-attachments/assets/e1649e87-c4b4-4aff-9ea7-895ec1082bee)

ENDPOINT 2
REQUEST URL - GET http://localhost:8080/api/rewards/customer/1/monthly?startDate=2025-01-01&endDate=2025-03-31

 SAMPLE RESPONSE 
 {
    "2025-02": 250,
    "2025-01": 115
}


![image](https://github.com/user-attachments/assets/4955cd1f-ac5a-4774-abd0-502714b6d58b)
