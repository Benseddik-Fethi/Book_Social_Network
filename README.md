# Book Social Network

Book Social Network is a full stack application for sharing books with your friends. Users can register, manage their personal library, borrow books from other members and leave feedback. The project is split into a Spring Boot REST API and two front‑end clients (Angular and React).

## Features

- User registration with email confirmation
- JWT based authentication
- Manage your own books (CRUD)
- Borrow and return books
- Upload book cover images
- Leave feedback on books
- REST API documented with Swagger

## Technology Stack

- **Backend**: Java 17, Spring Boot 3, Spring Security, JPA/Hibernate, Liquibase, MapStruct, OpenFeign
- **Database**: PostgreSQL
- **Frontend**: Angular 17 (`book-network-ui`) and React + Vite (`book-network-ui-react`)
- **Dev Tools**: Docker Compose (PostgreSQL & MailDev)

## Repository Layout

```
Book_Social_Network/
├── book-network/             # Spring Boot API
├── book-network-ui/          # Angular front-end
├── book-network-ui-react/    # React front-end
├── docker-compose.yml        # Development services
└── README.md
```

## Getting Started

1. **Start Docker services**
   ```bash
   docker-compose up -d
   ```
   This launches PostgreSQL (port `5432`) and MailDev (web UI on `http://localhost:1080`).

2. **Run the API**
   ```bash
   cd book-network
   ./mvnw spring-boot:run
   ```
   The API will be available at `http://localhost:8080/api/v1` and Swagger at `/swagger-ui.html`.

3. **Run the Angular UI**
   ```bash
   cd book-network-ui
   npm install
   npm start
   ```
   The Angular application runs on `http://localhost:4200`.

4. **Run the React UI**
   ```bash
   cd book-network-ui-react
   npm install
   npm run dev
   ```
   The React application runs on `http://localhost:5173`.

## License

This project is licensed under the [MIT License](LICENSE).


