# 📝 Notes App - Spring Boot CRUD Application

This is a simple **Notes Management** REST API built using **Spring Boot**, demonstrating complete CRUD operations with enum handling, exception management, and best practices like builder pattern and structured responses.

---

## 🚀 Tech Stack

- **Spring Boot** (v3+)
- **Spring Web**
- **Spring Data JPA**
- **H2 Database** (in-memory)
- **Lombok**
- **Java 17+**

---

## ✅ Features

- Create, Read, Update, and Delete notes
- Enum-based `Level` field: `LOW`, `MEDIUM`, `HIGH`
- Custom structured response using `HttpResponse<T>`
- Global exception handling via `@RestControllerAdvice`
- Clean and RESTful URI design
- Auto-generated timestamps (`createdAt`, `updatedAt`)

---

## 📁 API Endpoints

| Method | Endpoint             | Description             |
|--------|----------------------|-------------------------|
| POST   | `/note/create`       | Create a new note       |
| GET    | `/note/{id}`         | Get a single note       |
| GET    | `/note/all`          | Get all notes           |
| PUT    | `/note/update/{id}`  | Update a note (partial) |
| DELETE | `/note/delete/{id}`  | Delete a note by ID     |

---

## 📌 Enum `Level`

Enum values accepted in the payload:

```json
{
  "level": "HIGH" // or LOW, MEDIUM
}
