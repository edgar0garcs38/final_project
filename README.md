# Savings & Loan Cooperative: Web Banking System

A Java web application for a savings and loan cooperative. Members can track their **contributions** (*aportaciones*) and **loans** (*préstamos*), and administrators manage users and generate reports. This was the final project for my Java web development course.

## Features
- **Login with role-based access:** three roles (`admin`, `socio` = member, `cliente` = client), each with its own dashboard
- **Security filters:** `AuthFilter` blocks unauthenticated users and `RoleFilter` restricts each section to its role, redirecting to an access-denied page
- **Admin:** create, list and delete users, register contributions and loans, view financial summaries
- **Members and clients:** view their own contributions, loans and financial summary
- **PDF reports** of contributions and loans, generated with iText
- Layered architecture: servlets (controllers) → services → DAOs → MySQL via JDBC

## Tech stack
Java 21 · Jakarta EE (Servlets, JSP, Filters) · JDBC · MySQL/MariaDB · iText PDF · Bootstrap · Maven · Apache Tomcat

## Project structure
```
src/main/java/org/edgar/interfazbanco/
├── controller/   # Servlets: login, users, contributions, loans, reports
├── filter/       # AuthFilter, RoleFilter
├── service/      # Business logic
├── dao/          # Database access (JDBC)
├── models/       # Usuario, Aportacion, Prestamo, ResumenFinanciero
└── util/         # Database connection
src/main/webapp/
├── admin/  socio/  cliente/   # Views per role
└── Login.jsp
```

## Run it
1. Create a MySQL database named `bddbanco` and set your credentials in `util/Conexion.java`
2. Build and deploy to Tomcat:
   ```bash
   ./mvnw clean package
   ```
3. Open `http://localhost:8080/interfaz_banco/`

An earlier version of this project, built as a team, is in [interfaz_banco](https://github.com/edgar0garcs38/interfaz_banco).
