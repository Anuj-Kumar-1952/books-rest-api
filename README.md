# 📚 Books REST API 

A Spring Boot-based RESTful web service for managing a collection of books. It provides basic CRUD operations and demonstrates usage of Spring Data JPA.

## 🔧 Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL Database
- Maven
- Postman (for API testing)

## 🚀 Features
- Create, Read, Update, Delete books
- API designed with REST principles
- Exception handling with proper HTTP codes
- Project structure follows clean architecture

📮 API Endpoints
| Method | Endpoint               | Description       |
| ------ | ---------------------- | ----------------- |
| GET    | `/api/allbooks`        | Get all books     |
| GET    | `/api/book/{id}`       | Get book by ID    |
| POST   | `/api/addbook`         | Create a new book |
| PUT    | `/api/updatebook/{id}` | Update a book     |
| DELETE | `/api/delete/{id}`     | Delete a book     |
| DELETE | `/api/deleteAll`       | Delete all book   |


📦 How to Run
#1. clone the repository
git clone https://github.com/Anuj-Kumar-1952/books-rest-api.git
cd books-rest-api

#2. Configure the database (optional if using H2)
#3. ./mvnw spring-boot:run

🧪 Testing
Tested using Postman
## 📩 Postman Collection
You can download and import the API collection to test all endpoints:  
[📥 BooksAPI.postman_collection.json](BooksAPI.postman_collection.json)
> Open Postman → Import → Choose the file
