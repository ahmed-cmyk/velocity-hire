# VelocityHire - A Spring Boot Job Portal

VelocityHire is a robust job portal application built using Spring Boot, designed to connect job seekers with employers. It features a comprehensive backend with RESTful APIs, database integration, security, and asynchronous processing capabilities.

## Features

*   **User Management:** Secure user authentication and authorization (Admin, User roles).
*   **Job Management:** Create, view, update, and delete job listings.
*   **Application Management:** Candidates can apply for jobs, and employers can manage applications.
*   **Notifications:** Asynchronous notification system for job creation and applications.
*   **API Documentation:** Integrated Swagger UI for easy API exploration and testing.
*   **Database Integration:** Persistent storage using MySQL.
*   **Caching:** Utilizes Redis for caching frequently accessed data.
*   **Message Queuing:** Implements RabbitMQ for asynchronous event processing.

## Technologies Used

*   **Backend:** Java 17, Spring Boot
*   **Database:** MySQL
*   **ORM:** Spring Data JPA, Hibernate
*   **Security:** Spring Security (JWT based authentication)
*   **API Documentation:** Springdoc OpenAPI (Swagger UI)
*   **Messaging:** RabbitMQ
*   **Caching:** Redis
*   **Build Tool:** Gradle
*   **Utilities:** Lombok

## Project Structure

The project follows a standard Spring Boot application structure:

```
src/main/java/com/velocity/velocityhire/
├── config/             # Application configuration (Security, RabbitMQ, Redis, Web)
├── controller/         # REST API endpoints
├── dto/                # Data Transfer Objects for API requests and responses
├── entity/             # JPA Entities representing database tables
├── enums/              # Enumerations (e.g., JobStatus)
├── event/              # Application events (e.g., CandidateAppliedEvent)
├── exception/          # Custom exception handling
├── repository/         # Spring Data JPA repositories for database interaction
├── service/            # Business logic and service layer
│   └── async/          # Asynchronous workers (Job Indexing, Notifications)
├── util/               # Utility classes (JWT, Mapper, Cache)
└── VelocityHireApplication.java # Main Spring Boot application entry point
```

## Getting Started

### Prerequisites

*   Java Development Kit (JDK) 17 or higher
*   Gradle (usually bundled with Spring Boot projects)
*   MySQL Server
*   Redis Server
*   RabbitMQ Server

### Setup

1.  **Clone the repository:**
    ```bash
    git clone <repository_url>
    cd velocityHire
    ```

2.  **Database Configuration:**
    *   Create a MySQL database named `velocityhire_db`.
    *   Update the `src/main/resources/application.properties` file with your MySQL username and password:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/velocityhire_db?useSSL=false&serverTimezone=UTC
        spring.datasource.username=root
        spring.datasource.password=your_mysql_password
        ```

3.  **Run the application:**
    ```bash
    ./gradlew bootRun
    ```
    The application will start on `http://localhost:8080` by default.

## API Documentation (Swagger UI)

Once the application is running, you can access the API documentation via Swagger UI:
`http://localhost:8080/swagger-ui.html`

