# Job Portal REST API

A production-grade REST API for a job portal built with Spring Boot, featuring JWT authentication and role-based access control.

## Tech Stack
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT (JSON Web Token)
- MySQL
- Maven

## Features
- JWT-based stateless authentication
- Role-based access control (ADMIN, RECRUITER, CANDIDATE)
- Job posting, updating, and deletion by recruiters
- Job application submission and tracking by candidates
- Global exception handling
- Bean Validation on all request inputs
- CORS configuration for frontend integration

## Project Structure
src/main/java/com/kavisha/project3/
├── controller/        
├── service/           
├── repository/        
├── entity/            
├── dto/               
├── security/          
└── exception/         

## Getting Started

### Prerequisites
- Java 17+
- MySQL
- Maven

### Setup

1. Clone the repository
git clone https://github.com/hirwanikavisha/job-portal-rest-api.git
cd job-portal-rest-api

2. Create a MySQL database
CREATE DATABASE project3_db;

3. Create application.properties in src/main/resources/ with your credentials
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
jwt.secret=your_jwt_secret_min_32_characters

4. Run the application
mvn spring-boot:run

Server starts at http://localhost:8080

## API Endpoints

### Auth (Public)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /users/register | Register new user |
| POST | /users/login | Login and get JWT token |

### Users (Admin only)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /users | Get all users |
| GET | /users/{id} | Get user by ID |
| PUT | /users/{id} | Update user |
| DELETE | /users/{id} | Delete user |

### Jobs (Authenticated)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /jobs | Get all jobs |
| GET | /jobs/{id} | Get job by ID |
| POST | /jobs | Create job (Recruiter/Admin) |
| PUT | /jobs/{id} | Update job (Recruiter/Admin) |
| DELETE | /jobs/{id} | Delete job (Recruiter/Admin) |

### Applications (Authenticated)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /applications | Apply for a job |
| GET | /applications | Get all applications |
| GET | /applications/{id} | Get application by ID |
| GET | /applications/candidate/{id} | Get by candidate |
| GET | /applications/job/{id} | Get by job |
| DELETE | /applications/{id} | Delete application |

## Sample Usage

### Register
POST /users/register
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123",
  "role": "CANDIDATE"
}

### Login
POST /users/login
{
  "email": "john@example.com",
  "password": "password123"
}

### Login Response
{
  "userId": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "role": "CANDIDATE",
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}

### Use token for protected endpoints
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...

### Create a job
POST /jobs
Authorization: Bearer your_token_here
{
  "title": "Backend Developer",
  "company": "Tech Corp",
  "location": "Bangalore",
  "salary": 800000,
  "description": "Looking for a Java Spring Boot developer",
  "recruiterId": 1
}

### Apply for a job
POST /applications
Authorization: Bearer your_token_here
{
  "jobId": 1,
  "candidateId": 2
}

## Security
- Passwords hashed using BCrypt
- JWT tokens signed with HMAC-SHA256
- Stateless authentication — no sessions
- Role-based endpoint protection using Spring Security

## Author
Kavisha Hirwani
- GitHub: https://github.com/hirwanikavisha
- LinkedIn: https://www.linkedin.com/in/kavisha-hirwani-8623b9331/
- LeetCode: https://leetcode.com/u/kavishahirwani
