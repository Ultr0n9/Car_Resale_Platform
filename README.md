A comprehensive backend solution for managing car listings, user bookings, authentication, and notifications — designed with scalability, modularity, and cloud integration in mind.
It demonstrates my understanding of REST API development, database design, authentication, and cloud integration.

🧩 Tech Stack

Java 17, Spring Boot, Spring Security, Hibernate (JPA)

MySQL – Relational Database

AWS S3 – Cloud Storage for Media Files

SendGrid / Twilio – Email & SMS Notifications

Docker – Containerization

GitHub Actions – CI/CD Pipeline


✨ Features

User registration and login with JWT authentication

Role-based access control (Admin, User, Agent)

Add, update, and delete car listings

Upload and manage multiple car images (stored on AWS S3)

Email and SMS notifications using SendGrid and Twilio

Real-time communication via Kafka

Containerized with Docker and managed using Terraform

APIs tested and documented in Postman



🚀 How to Run

Clone the repository

git clone https://github.com/your-username/car-rental-system.git
cd car-rental-system


Update configuration

Add your database, AWS, SendGrid, and Twilio credentials in application.properties.

Build and Run

mvn clean install
mvn spring-boot:run


Test the APIs

Visit: http://localhost:8080/api/v1

Use Postman to test endpoints.
