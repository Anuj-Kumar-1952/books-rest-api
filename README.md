# 📚 Books REST API 

A Spring Boot-based RESTful web service for managing a collection of books. It provides basic CRUD operations and demonstrates usage of Spring Data JPA.

## 🔧 Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL Database
- Maven
- lombok
- Jakarta validation
- Postman (for API testing)

## 🚀 Features
✅ CRUD operations for Books and Authors  
✅ Many-to-many relationship: Books ↔ Authors  
✅ DTOs for clean API responses  
✅ Validation on incoming requests  
✅ Global exception handling with meaningful error messages  
✅ Fully tested Postman collection for API examples  
✅ Ready to deploy to any Java runtime environment

📦 How to Run
#1. clone the repository
git clone https://github.com/Anuj-Kumar-1952/books-rest-api.git

#2. Navigate into the Project
cd books-rest-api

#3. Build the Project
./mvnw clean install

#4. Configure the database (optional if using H2)

#5. Run the Application
./mvnw spring-boot:run
By default, the API will start on http://localhost:8080/

🗂️ API Endpoints
📖 Books
GET /api/books → List all books

GET /api/books/{id} → Get book details by ID

POST /api/books → Create a new book with existing authors (provide author IDs)

PUT /api/books/{id} → Update book information

DELETE /api/books/{id} → Delete a book

DELETE /api/books → Delete all books

✍️ Authors
GET /api/authors → List all authors

GET /api/authors/{id} → Get author details by ID

POST /api/authors → Create a new author

PUT /api/authors/{id} → Update author information

DELETE /api/authors/{id} → Delete an author

DELETE /api/authors → Delete all authors

🛡️ Error Handling
This project uses a global exception handler, returning consistent and meaningful JSON error responses (e.g., 404 for resource not found, 400 for validation errors).

🧪 Testing
Tested using Postman
## 📩 Postman Collection
You can download and import the API collection to test all endpoints: 
[📥 books_restapi_collection.json](./postman/books_restapi_collection.json)
> Open Postman → Import → Choose the file

🛠️ Future Enhancements
✅ Unit & integration tests with JUnit/Mockito
✅ Pagination & filtering
✅ Spring Security for authentication
✅ API documentation with Swagger/OpenAPI

👤 Author
Developed by Anuj Kumar
Feel free to open issues or contribute!
