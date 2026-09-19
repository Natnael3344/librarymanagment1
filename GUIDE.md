# Developer Guide

This guide covers how to configure and run the Library Management System
locally, how the codebase is organized, and things a new contributor should
know before making changes. Everything below reflects the code as it
actually exists in this repository.

## 1. What this project is

A Spring Boot 2.7.3 (Java 17) REST API for a library's inventory and
lending workflow. It has no web UI — no Thymeleaf templates, no static
`resources/static` assets, nothing beyond `@RestController` classes
returning JSON. Client applications (a separate frontend, mobile app, etc.)
are expected to consume the endpoints listed in
[API_REFERENCE.md](API_REFERENCE.md).

## 2. Database configuration (required before first run)

### There is no `application.properties` in this repo

Run `git log -- 'src/main/resources/*'` and you'll see a commit titled
*"Delete application.properties"* — the file was intentionally removed
(most likely to stop committing datasource credentials), and
`src/main/resources` doesn't exist at all anymore. Spring Boot will fail to
start without a datasource configured, so **you must create this file
yourself** the first time you set up the project:

```
src/main/resources/application.properties
```

### Choosing a database

Three JDBC drivers are declared in `pom.xml`: `postgresql`,
`mysql-connector-java`, and `h2` (all `runtime` scope). Only one is used at
a time — whichever `spring.datasource.url` you configure. The project's
own `backup.sql` file is a **MySQL 8** dump (database name `lb`), so MySQL
is the path of least resistance if you want to use the included sample
data as-is.

Example `application.properties` for local MySQL development (adapted from
keys that existed in this project's git history before the file was
deleted):

```properties
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/lb?useSSL=false&serverTimezone=UTC
spring.datasource.username=your_mysql_user
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format-sql=true
spring.jpa.hibernate.naming.implicit-strategy=org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```

To use the bundled sample data instead of relying on
`ddl-auto=update` to create empty tables:

```bash
mysql -u your_mysql_user -p -e "CREATE DATABASE lb"
mysql -u your_mysql_user -p lb < backup.sql
```

`backup.sql` creates and seeds all seven tables the entities map to:
`assets`, `users`, `teams`, `roles`, `permissions`, `permission_role`,
`role_user`, and `transactions`.

If you'd rather not install MySQL, swap in the H2 or PostgreSQL driver:
point `spring.datasource.url` at an H2 file/in-memory URL or a Postgres
instance, set the matching `driver-class-name` and
`hibernate.dialect`/`database-platform`, and let
`spring.jpa.hibernate.ddl-auto=update` create the schema from the JPA
entities (note that `backup.sql`'s data won't apply to a non-MySQL schema
without adaptation).

### Environment-variable based config (deployment)

The repo ships a `Procfile` (`web: java -Dserver.port=$PORT -jar
target/librarymanagment-0.0.1-SNAPSHOT.jar`) and `system.properties`
(`java.runtime.version=18`) — Heroku's convention for a Java web dyno. This
strongly suggests the deployed version of this app supplies datasource
settings via environment variables (e.g. Heroku config vars) rather than a
committed properties file, which is consistent with why
`application.properties` was deleted from source control.

**Note the Java version mismatch:** `pom.xml` sets `<java.version>17`,
but `system.properties` requests `java.runtime.version=18` for the Heroku
buildpack. Locally, build and run with JDK 17 to match the Maven
configuration; treat `system.properties` as a deployment-only setting.

## 3. Running the app

```bash
./mvnw clean package        # build target/librarymanagment-0.0.1-SNAPSHOT.jar
./mvnw spring-boot:run       # or run the jar directly with `java -jar`
```

`mvnw`/`mvnw.cmd` are the Maven Wrapper (Maven 3.8.6, see
`.mvn/wrapper/maven-wrapper.properties`), so a local Maven install isn't
required.

Once started (assuming no custom `server.servlet.context-path`), hit
`http://localhost:8080/view` to list assets, `POST
http://localhost:8080/saveUser` to create a user, etc. — see
[API_REFERENCE.md](API_REFERENCE.md) for the full endpoint list.

## 4. How the code is organized

The codebase follows a conventional layered Spring Boot structure, repeated
for each of the seven domain entities (`Assets`, `Users`, `Teams`, `Roles`,
`Permissions`, `Permission_Role`, `Role_User`, `Transactions`):

```
controller/  →  @RestController classes: define HTTP routes, deserialize
                 request bodies, and either call a repository directly
                 (for simple save/list) or a service (for edit/delete).
service/     →  Plain interfaces (e.g. AssetsService) declaring the
                 business operations for that entity.
service/services/
             →  @Service (@Primary) implementations of those interfaces
                 (e.g. AssetsServices), which delegate to a repository.
repository/  →  Spring Data `JpaRepository<Entity, Long>` interfaces —
                 no custom queries are defined anywhere; only inherited
                 methods (`findAll`, `findById`, `save`, `existsById`,
                 `deleteById`) are used.
entity/      →  `@Entity` classes mapped with `@Table(name = "...")` to
                 the tables created by backup.sql, using Lombok
                 `@Getter`/`@Setter` instead of hand-written accessors.
exceptions/  →  NotFoundException (a RuntimeException) and
                 ResponseHandler (builds the {message, status, data|errors}
                 JSON envelope used by edit/delete endpoints).
```

Two inconsistencies to be aware of when extending this code:

- **Not every controller follows the full CRUD pattern.**
  `Role_UserController` only exposes `GET /viewRolesUser` (no
  save/edit/delete route, even though the service/repository support it),
  and `Permission_RoleController` is a completely empty class with no
  endpoints at all, despite `Permission_Role` having a full entity,
  repository, and service implementation behind it.
- **ID generation is inconsistent across entities.** `Users`, `Roles`,
  `Role_User`, and `Transactions` use `@GeneratedValue` (DB/Hibernate
  assigns the id). `Assets`, `Permissions`, and `Teams` declare `@Id`
  without `@GeneratedValue`, meaning the caller must supply an `id` in the
  POST body for those three.

## 5. Security note

There is no `spring-boot-starter-security` dependency and no custom
authentication filter anywhere in the code. Every endpoint in
[API_REFERENCE.md](API_REFERENCE.md) is open to any caller who can reach
the server, and `Users.password` is stored and returned as plain text in
JSON responses (no hashing, no `@JsonIgnore`). Do not deploy this as-is
against real user data without adding an auth layer.

## 6. Testing

The only test present is
`src/test/java/com/example/librarymanagment/LibrarymanagmentApplicationTests.java`,
a single `@SpringBootTest` "context loads" smoke test with no assertions
about behavior. Because it boots the full Spring context, running it (`./mvnw
test`) still requires a working datasource connection as configured in
step 2.

## 7. Known rough edges worth knowing before contributing

- Several files contain large blocks of commented-out code (e.g.
  `@OneToMany`/`@ManyToOne` relationship mappings in `Assets`, `Users`,
  `Roles`, `Teams`, `Role_User`) — the entities currently use plain foreign
  key columns (`team_id`, `user_id`, `role_id`, etc. as `int`/`Long`)
  instead of JPA object relationships.
- `NotFoundException` is thrown by every service's `findXById` method, but
  none of those methods are wired to a controller route, so that exception
  path is currently unreachable via HTTP (see
  [API_REFERENCE.md](API_REFERENCE.md) for details).
- `.idea/` (JetBrains project metadata) is committed to the repository;
  treat it as machine-specific and avoid relying on it for build
  configuration.
