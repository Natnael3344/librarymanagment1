# Library Management System

A Spring Boot REST API for managing a library's inventory and lending
operations. It exposes CRUD endpoints for library assets (books/items),
users (members/staff), teams, role-based permissions, and checkout/return
transactions. Data is persisted through Spring Data JPA to a relational
database (MySQL in the project's own database dump, with PostgreSQL and H2
drivers also on the classpath).

> The Java package and Maven artifact are named `librarymanagment` (missing
> the second "e") throughout the codebase — this is intentional, not a typo
> in this document.

## Features

Verified directly from the controllers, services, and repositories in
`src/main/java/com/example/librarymanagment`:

- **Assets (library items)** — create, list, update, and delete book/asset
  records (`AssetsController`), including bibliographic fields such as
  author, publication, edition, language, page count, cost, an RFID tag,
  and a "danger level" flag.
- **Users** — create, list, update, and delete library users, with unique
  email/mobile/RFID-tag fields (`UsersController`, `Users` entity).
- **Teams** — create, list, update, and delete teams/branches that assets,
  users, and transactions can be associated with via a `team_id`
  (`TeamsController`).
- **Roles & Permissions (RBAC scaffolding)** — create, list, update, and
  delete `Roles` and `Permissions` records (`RolesController`,
  `PermissionsController`). A `Role_User` join entity/repository links
  users to roles, with a read-only listing endpoint
  (`Role_UserController`). A `Permission_Role` join entity and repository
  also exist to link permissions to roles, but **no REST endpoints are
  wired up for it** — see [API_REFERENCE.md](API_REFERENCE.md).
- **Transactions (checkout/return)** — create, list, update, and delete
  transaction records that track which asset was issued to which
  user/member, the issue date, expected return date, actual return date,
  and whether it has been returned (`TransactionsController`,
  `Transactions` entity).
- **Consistent JSON error/success envelopes** for update/delete operations
  via `ResponseHandler`, which wraps responses as
  `{ "message": ..., "status": ..., "data" | "errors": ... }`.
- **Database-backed sample data**: a MySQL dump (`backup.sql`) with the
  full schema and seed rows for all seven tables is included for local
  development.

There is **no authentication/authorization layer** — no Spring Security
dependency is declared, no login endpoint exists, and none of the
controllers restrict access, even though `Users` stores a `password`
field. See [GUIDE.md](GUIDE.md) for details.

## Tech Stack

- **Java 17** (the Maven build targets Java 17; note that
  `system.properties` requests a Java 18 runtime for Heroku deploys — see
  [GUIDE.md](GUIDE.md) for this discrepancy)
- **Spring Boot 2.7.3**
  - `spring-boot-starter-web` — REST controllers
  - `spring-boot-starter-data-jpa` and `spring-boot-starter-data-jdbc`
  - `spring-boot-devtools`
- **Hibernate / Spring Data JPA** for ORM
- **Database drivers on the classpath**: `postgresql`, `mysql-connector-java`,
  and `h2` (runtime scope) — only one is actually used at a time, selected
  by the datasource configuration you provide (see GUIDE.md)
- **Lombok** (`@Getter`/`@Setter` on entities)
- **Maven** (with the Maven Wrapper, `mvnw`/`mvnw.cmd`, pinned to Maven
  3.8.6)
- **JUnit 5 / spring-boot-starter-test** for testing

## Project Structure

```
librarymanagment1/
├── pom.xml                     # Maven build (Spring Boot 2.7.3, Java 17)
├── mvnw, mvnw.cmd               # Maven Wrapper scripts
├── Procfile                     # Heroku process definition (web dyno)
├── system.properties            # Heroku JDK version pin
├── backup.sql                   # MySQL schema + seed data dump (db "lb")
├── HELP.md                      # Default Spring Initializr help file
├── src/main/java/com/example/librarymanagment/
│   ├── LibraryManagementApplication.java   # @SpringBootApplication entry point
│   ├── controller/               # @RestController classes (HTTP layer)
│   │   ├── AssetsController.java
│   │   ├── UsersController.java
│   │   ├── TeamsController.java
│   │   ├── RolesController.java
│   │   ├── PermissionsController.java
│   │   ├── Permission_RoleController.java  # empty stub, no endpoints
│   │   ├── Role_UserController.java        # read-only listing only
│   │   └── TransactionsController.java
│   ├── entity/                   # JPA @Entity classes, one per DB table
│   │   ├── Assets.java, Users.java, Teams.java, Roles.java,
│   │   │   Permissions.java, Permission_Role.java, Role_User.java,
│   │   │   Transactions.java
│   ├── repository/               # Spring Data JpaRepository interfaces
│   ├── service/                  # Service interfaces
│   │   └── services/             # Service implementations (@Service)
│   └── exceptions/
│       ├── NotFoundException.java
│       └── ResponseHandler.java  # Shared success/error JSON envelope
├── src/test/java/.../LibrarymanagmentApplicationTests.java  # context-load smoke test
└── src/main/resources/           # NOT present in this repo — see GUIDE.md
```

## Getting Started

### Prerequisites

- JDK 17 (the version declared in `pom.xml`)
- A database: MySQL, PostgreSQL, or H2 (drivers for all three are already
  on the classpath — you only need to configure and run the one you pick)
- No local Maven install is required; the project ships the Maven Wrapper.

### Build

```bash
./mvnw clean package
```

This produces `target/librarymanagment-0.0.1-SNAPSHOT.jar`.

### Configure a database

This repository does **not** contain a `src/main/resources/application.properties`
(or `.yml`) file — it was intentionally removed from version control (see
`git log`). You must create one yourself before the app can start, since
Spring Data JPA needs a datasource. See [GUIDE.md](GUIDE.md) for a concrete
example and how to load `backup.sql` for sample data.

### Run

```bash
./mvnw spring-boot:run
```

or, after packaging:

```bash
java -jar target/librarymanagment-0.0.1-SNAPSHOT.jar
```

By default Spring Boot serves on `http://localhost:8080` (no context path
is configured in the current codebase). See
[API_REFERENCE.md](API_REFERENCE.md) for the full list of endpoints.

### Deploying

The repo includes a `Procfile` (`web: java -Dserver.port=$PORT -jar
target/librarymanagment-0.0.1-SNAPSHOT.jar`) and a `system.properties`
(`java.runtime.version=18`), which together are Heroku's convention for
running a Java web process — this suggests the project was previously
deployed to Heroku.

## Documentation

- [API_REFERENCE.md](API_REFERENCE.md) — every HTTP endpoint, verified
  against the controller source.
- [GUIDE.md](GUIDE.md) — configuration, database setup, and how the
  layers of the codebase fit together.
