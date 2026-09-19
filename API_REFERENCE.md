# API Reference

This document lists every HTTP endpoint actually defined in
`src/main/java/com/example/librarymanagment/controller`. There is no global
`@RequestMapping` base path anywhere in the codebase, so every path below is
relative to the server root (default `http://localhost:8080`), unless you
add a `server.servlet.context-path` in your own `application.properties`
(see [GUIDE.md](GUIDE.md)).

All request/response bodies are JSON (`spring-boot-starter-web` with the
default Jackson converter). None of the endpoints require authentication —
there is no security layer in this project.

Two response shapes are used, depending on the endpoint:

- **Save / list endpoints** return the entity (or list of entities) directly
  as JSON, with an implicit `200 OK`.
- **Edit / delete endpoints** return a JSON envelope produced by
  `ResponseHandler.handleResponse`:

  ```json
  {
    "message": "Successfully edit product",
    "status": 200,
    "data": { /* the updated entity, only present on success */ }
  }
  ```

  On failure, the same shape is returned with an `errors` key instead of
  `data`, and a `400 Bad Request` status (either because the record doesn't
  exist, or because an exception was caught).

---

## Assets

Source: `AssetsController.java`. Backing entity: `Assets` (table `assets`).

| Method | Path            | Body / Params                  | Description |
|--------|-----------------|---------------------------------|--------------|
| POST   | `/save`         | `Assets` JSON body              | Creates (or upserts, since `id` is client-supplied and not `@GeneratedValue`) an asset. Returns the saved `Assets` object. |
| GET    | `/view`         | —                                | Returns a JSON array of all assets. |
| PUT    | `/edit`         | `Assets` JSON body (validated)   | Updates an asset if its `id` already exists. Returns the `ResponseHandler` envelope. |
| DELETE | `/delete/{id}`  | `id` path variable              | Deletes the asset with the given id. Returns the `ResponseHandler` envelope. |

`Assets` fields: `id`, `created_at`, `updated_at`, `deleted_at`, `name`,
`author`, `publication`, `edition`, `cost`, `language`, `pages`,
`description`, `rfid_tag`, `danger_level` (int), `team_id` (int).

---

## Users

Source: `UsersController.java`. Backing entity: `Users` (table `users`).

| Method | Path                | Body / Params                | Description |
|--------|---------------------|-------------------------------|--------------|
| POST   | `/saveUser`         | `Users` JSON body             | Creates a user (id is `@GeneratedValue`). Returns the saved `Users` object. |
| GET    | `/viewUser`         | —                              | Returns a JSON array of all users. |
| PUT    | `/editUser`         | `Users` JSON body (validated)  | Updates a user if its `id` exists. Returns the `ResponseHandler` envelope. |
| DELETE | `/deleteUser/{id}`  | `id` path variable             | Deletes the user with the given id. Returns the `ResponseHandler` envelope. |

`Users` fields: `id`, `created_at`, `updated_at`, `deleted_at`, `fname`,
`lname`, `email` (unique), `mobile` (unique), `email_verified_at`,
`password` (stored as plain text — no hashing is applied anywhere in the
code), `remember_token`, `rfid_tag` (unique), `team_id` (int).

---

## Teams

Source: `TeamsController.java`. Backing entity: `Teams` (table `teams`).

| Method | Path                 | Body / Params                | Description |
|--------|----------------------|-------------------------------|--------------|
| POST   | `/saveTeams`         | `Teams` JSON body              | Creates a team. Returns the saved `Teams` object. |
| GET    | `/viewTeams`         | —                               | Returns a JSON array of all teams. |
| PUT    | `/editTeams`         | `Teams` JSON body (validated)   | Updates a team if its `id` exists. Returns the `ResponseHandler` envelope. |
| DELETE | `/deleteTeams/{id}`  | `id` path variable              | Deletes the team with the given id. Returns the `ResponseHandler` envelope. |

`Teams` fields: `id`, `created_at`, `updated_at`, `deleted_at`,
`name` (not null).

---

## Roles

Source: `RolesController.java`. Backing entity: `Roles` (table `roles`).

| Method | Path                 | Body / Params                | Description |
|--------|----------------------|-------------------------------|--------------|
| POST   | `/saveRoles`         | `Roles` JSON body              | Creates a role. Returns the saved `Roles` object. |
| GET    | `/viewRoles`         | —                               | Returns a JSON array of all roles. |
| PUT    | `/editRoles`         | `Roles` JSON body (validated)   | Updates a role if its `id` exists. Returns the `ResponseHandler` envelope. |
| DELETE | `/deleteRoles/{id}`  | `id` path variable              | Deletes the role with the given id. Returns the `ResponseHandler` envelope. |

`Roles` fields: `id`, `created_at`, `updated_at`, `deleted_at`, `title`.

---

## Permissions

Source: `PermissionsController.java`. Backing entity: `Permissions`
(table `permissions`).

| Method | Path                       | Body / Params               | Description |
|--------|----------------------------|-------------------------------|--------------|
| POST   | `/savePermissions`         | `Permissions` JSON body       | Creates a permission. Returns the saved `Permissions` object. |
| GET    | `/viewPermissions`         | —                              | Returns a JSON array of all permissions. |
| PUT    | `/editPermissions`         | `Permissions` JSON body (validated) | Updates a permission if its `id` exists. Returns the `ResponseHandler` envelope. |
| DELETE | `/deletePermissions/{id}`  | `id` path variable             | Deletes the permission with the given id. Returns the `ResponseHandler` envelope. |

`Permissions` fields: `id`, `created_at`, `updated_at`, `deleted_at`,
`title`.

---

## Role ↔ User assignments (read-only)

Source: `Role_UserController.java`. Backing entity: `Role_User`
(table `role_user`).

| Method | Path              | Body / Params | Description |
|--------|-------------------|----------------|--------------|
| GET    | `/viewRolesUser`  | —              | Returns a JSON array of all `role_user` link rows (`id`, `user_id`, `role_id`). |

There is no create/update/delete endpoint for this resource, even though
`Role_UserService` and `Role_UserRepository` define the methods for it —
only the controller method was implemented.

---

## Permission ↔ Role assignments — no endpoints

`Permission_RoleController.java` exists but is an **empty class with no
annotations and no methods**:

```java
public class Permission_RoleController {
}
```

The `Permission_Role` entity (table `permission_role`, fields `id`,
`permission_id`, `role_id`) and its repository/service do exist, but there
is currently no way to reach this data over HTTP.

---

## Transactions (checkout / return records)

Source: `TransactionsController.java`. Backing entity: `Transactions`
(table `transactions`).

| Method | Path                        | Body / Params                        | Description |
|--------|-----------------------------|----------------------------------------|--------------|
| POST   | `/saveTransaction`          | `Transactions` JSON body               | Creates a transaction record. Returns the saved `Transactions` object. |
| GET    | `/viewTransaction`          | —                                       | Returns a JSON array of all transactions. |
| PUT    | `/editTransaction`          | `Transactions` JSON body (validated)    | Updates a transaction if its `id` exists. Returns the `ResponseHandler` envelope. |
| DELETE | `/deleteTransaction/{id}`   | `id` path variable                      | Deletes the transaction with the given id. Returns the `ResponseHandler` envelope. |

`Transactions` fields: `id`, `created_at`, `updated_at`, `deleted_at`,
`isReturned` (Integer, acts as a boolean flag), `returnDate` (expected
return date, `String`, not null), `returnedOn` (actual return date,
`String`), `issueDate` (`String`, not null), `asset_id`, `team_id`,
`user_id`, `member_id`.

---

## Error handling

There is no `@ControllerAdvice`/global exception handler in the project.
Every `*Services` implementation defines a `findXById(Long id)` method that
throws `NotFoundException` via `.orElseThrow(...)` when the id doesn't
exist, but **none of these finder methods are ever called from a
controller** — there is no `GET /.../{id}` single-record endpoint for any
resource. Controllers instead call `existsById`/`deleteById` on the
repository directly for edit/delete, and wrap the call in a generic
`try/catch (Exception e)` that turns any failure into a `400 Bad Request`
via `ResponseHandler`. As a result, `NotFoundException` is effectively
unreachable dead code in the current wiring.
