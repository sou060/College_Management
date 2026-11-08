# College Management System

Spring Boot application for managing students, professors, subjects, and admission records. The browser UI is served by the application. API operations require an administrator session and CSRF token.

## Local setup

Requirements: Java 17 or newer, MySQL, and an empty database/schema for a new install.

Set configuration in your shell or local secret manager. Do not commit credentials:

```sh
export DB_URL='jdbc:mysql://127.0.0.1:3306/college_db'
export DB_USERNAME='college_app'
export DB_PASSWORD='replace-with-a-local-secret'
export DB_MIGRATION_USERNAME="$DB_USERNAME"
export DB_MIGRATION_PASSWORD="$DB_PASSWORD"
export APP_ADMIN_USERNAME='admin'
export APP_ADMIN_PASSWORD='replace-with-a-long-unique-secret'
export SESSION_COOKIE_SECURE=false
./mvnw spring-boot:run
```

`.env.example` lists the expected local variables. To use a local ignored `.env` file, copy and edit it, then load it into your shell before starting the app:

```sh
cp .env.example .env
# edit .env and replace every placeholder
set -a
. ./.env
set +a
./mvnw spring-boot:run
```

`SESSION_COOKIE_SECURE=false` is for local HTTP development only. Set it to `true` in deployments served over HTTPS. Never use the database root account. In production, use a runtime database account limited to application DML and a separate migration account with schema-change permissions; supply the latter as `DB_MIGRATION_USERNAME` and `DB_MIGRATION_PASSWORD`.

On an empty schema, Flyway applies `src/main/resources/db/migration/V1__initial_schema.sql`. Hibernate validates the resulting schema and does not alter it. The app fails to start if required database or administrator settings are missing.

After startup, open `http://localhost:8080`; the UI redirects unauthenticated API requests to the sign-in page. The bootstrap administrator password must contain at least 16 characters.

## Existing database

Back up the database first. Review the current schema and data against V1, including null values, duplicate admission records, join-table rows, and foreign keys. Do not activate the legacy profile until the database has been reconciled with that migration. Then start with `SPRING_PROFILES_ACTIVE=legacy-db`; Flyway will record the existing schema at version 1 without rewriting it. This profile is an adoption aid, not a schema repair tool.

The admission fee is still represented as a nonnegative integer because the original data does not define currency or fractional-unit semantics. Confirm the institution's currency and amount format before using this field for billing or payments.

## Production deployment

- Supply database and administrator credentials through a secret manager. Rotate any credentials previously stored in source control.
- Terminate TLS at a trusted reverse proxy or load balancer, keep `SESSION_COOKIE_SECURE=true`, and restrict database network access to the application.
- Keep actuator exposure limited to health and info. `/actuator/health` and its readiness/liveness probes are public for orchestrator checks; protect the rest of the application.
- Back up the database and test restoration before deploying schema changes. Apply future changes as new Flyway migrations; do not edit a migration that has already run.
- The current authentication setup supports one bootstrap administrator. Add the institution's identity provider and staff roles before sharing access across multiple staff accounts.
- List endpoints currently return all records. Add pagination and exercise the application against expected production data volume before launch.

## API

The current API base is `/api/v1`. The original `/api` paths remain as compatibility aliases:

- `/student` — create, read, update, delete, and enroll/link operations
- `/professor` — create, read, update, delete
- `/subject` — create, read, update, delete, and professor assignment
- `/admission` — create, list, and delete admission records
- `/auth/csrf` — obtains the CSRF token used by the browser client

Create/update request fields are validated. Errors use Problem Details JSON; expected data conflicts return HTTP 409.
