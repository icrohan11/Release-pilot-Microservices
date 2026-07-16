# ReleasePilot — Microservices

Software Product Lifecycle & Release Management Platform, refactored from the
monolith into Spring Cloud microservices.

**Stack:** Java 25 · Spring Boot 4.0.7 · Spring Cloud 2025.1.2 (Oakwood) · MySQL 8 · Maven (multi-module)

---

## 1. Architecture

```
                    ┌─────────────────┐
   client ────────► │   api-gateway   │  :8080   (validates JWT, injects X-Auth-User / X-Auth-Role)
                    └────────┬────────┘
                             │ lb:// (Eureka)
   ┌────────────┬────────────┼────────────┬────────────┬─────────────────┐
   ▼            ▼            ▼            ▼            ▼                 ▼
 auth        product      backlog       release        qa          communication
 :9001        :9002        :9003        :9004        :9005             :9006

   ▲                         (all register with)  ▲                 (all fetch config from)  ▲
   └──────────────► eureka-server :8761 ───────────┘                 config-server :8888 ────┘
```

- **config-server** (`:8888`) — Spring Cloud Config Server, `native` profile, serves every
  service's configuration from the local folder `config-server/src/main/resources/config-repo`.
- **eureka-server** (`:8761`) — Netflix Eureka service registry.
- **api-gateway** (`:8080`) — the single entry point. A `GlobalFilter`
  (`JwtAuthenticationGlobalFilter`) validates the JWT, then forwards the caller's
  identity to downstream services as trusted headers.
- **auth-service** — issues JWTs (login/register); the other services never parse tokens.

### Security flow (Spring Security + JWT)
1. `POST /auth/login` on **auth-service** verifies credentials (BCrypt) and returns a signed JWT (role in a claim).
2. Every subsequent call goes through the **gateway**, whose `GlobalFilter` verifies the
   token signature + expiry and injects `X-Auth-User` and `X-Auth-Role` headers.
3. Each service's `RoleBasedHeaderFilter` rebuilds the Spring Security context from those
   headers, and `SecurityConfig` enforces role-based access (`@EnableMethodSecurity` + `requestMatchers`).
4. On inter-service Feign calls, `FeignClientInterceptor` propagates the `Authorization`
   and identity headers so downstream authorization still works.
5. **Resilience4j** circuit breakers protect Feign calls (with fallbacks).

---

## 2. Services, ports & databases

| Module                  | Port | MySQL schema (auto-created) | Domain (modules) | Feign → |
|-------------------------|------|------------------------------|------------------|---------|
| config-server           | 8888 | —                            | Config Server (local folder) | — |
| eureka-server           | 8761 | —                            | Service registry | — |
| api-gateway             | 8080 | —                            | Routing + JWT GlobalFilter | — |
| auth-service            | 9001 | releasepilot_auth            | 4.1 Identity & Access (User, AuditLog) | — |
| product-service         | 9002 | releasepilot_product         | 4.2 Product & Roadmap (Product, RoadmapMilestone) | — |
| backlog-service         | 9003 | releasepilot_backlog         | 4.3 Backlog & Sprint (BacklogItem, Sprint, SprintItem) | product |
| release-service         | 9004 | releasepilot_release         | 4.4 Release Pkg + 4.5 Env & Promotion (ReleasePackage, PatchDependency, Environment, PromotionRequest, ChangeFreezeWindow) | product |
| qa-service              | 9005 | releasepilot_qa              | 4.6 QA Sign-Off & Gates (TestSuite, TestExecution, ReleaseGate) | release |
| communication-service   | 9006 | releasepilot_comm            | 4.7 Release Notes & Comms + 4.8 Notifications (ReleaseNote, CustomerAdvisory, Notification) | release |

Each service follows the same layered layout:
`common` (enums) · `entity` · `dto` (Bean Validation) · `repository`
(`ListCrudRepository` / `PagingAndSortingRepository` — never `JpaRepository`) ·
`mapper` (entity↔DTO) · `service` (interface + impl) · `controller` ·
`config` (SecurityConfig + Feign + FeignClientInterceptor + RoleBasedHeaderFilter + OpenApiConfig) ·
`exception` · `aspect` (AOP `LoggingAspect` → `logs/spring.log`).

---

## 3. Team-of-5 ownership

| Member | Owns |
|--------|------|
| Dev 1 | **auth-service** (+ shared security template) |
| Dev 2 | **product-service** |
| Dev 3 | **backlog-service** |
| Dev 4 | **release-service** (largest — 5 entities) |
| Dev 5 | **qa-service** + **communication-service** |
| Shared | config-server, eureka-server, api-gateway (infrastructure) |

---

## 4. Prerequisites

- JDK 25 (already installed).
- MySQL 8 running on `localhost:3306`, user `root` / password `root`
  (change credentials in `config-server/.../config-repo/application.properties`).
  Schemas are created automatically (`createDatabaseIfNotExist=true` + `ddl-auto=update`).

---

## 5. Run order (IMPORTANT)

Start in this order (each waits a few seconds to register/serve config):

1. **config-server**  → http://localhost:8888
2. **eureka-server**  → http://localhost:8761
3. **auth-service**, then **product-service**, **backlog-service**, **release-service**,
   **qa-service**, **communication-service** (any order among these)
4. **api-gateway** → http://localhost:8080

### From the command line (Maven wrapper of the monolith is reused)
```powershell
# from ReleasePilot-Microservices
..\ReleasePilot\mvnw.cmd -f config-server/pom.xml spring-boot:run
..\ReleasePilot\mvnw.cmd -f eureka-server/pom.xml spring-boot:run
..\ReleasePilot\mvnw.cmd -f auth-service/pom.xml spring-boot:run
# ...and so on for the remaining services, then api-gateway
```

### In Eclipse
1. **File → Import → Maven → Existing Maven Projects**, select the
   `ReleasePilot-Microservices` folder. Eclipse imports the parent + all 9 modules.
2. Let it download dependencies (first import).
3. Run each module's `*Application.java` as a **Java/Spring Boot App** in the order above
   (Run As → Java Application). Each module is an independent Spring Boot app.

---

## 6. Build & test everything

```powershell
# full multi-module build + all unit tests (JUnit 5 + Mockito)
..\ReleasePilot\mvnw.cmd clean test
```

Tests are pure unit tests (Mockito for services, standalone MockMvc for controllers) —
they need **no** database, config-server or Eureka running.

---

## 7. Trying it out

```
POST http://localhost:8080/auth/register    { "name":"Admin", "role":"Admin", "email":"admin@cts.com", "password":"admin123" }
POST http://localhost:8080/auth/login        { "email":"admin@cts.com", "password":"admin123" }   → returns { token, role, ... }

# use the token on every other call
GET  http://localhost:8080/products/all      Authorization: Bearer <token>
POST http://localhost:8080/products/save     Authorization: Bearer <token>   { ...product... }
```

Swagger UI per service (direct, bypassing the gateway): `http://localhost:<port>/swagger-ui.html`.

Circuit-breaker / health: `http://localhost:<port>/actuator/health`.
Logs: each service writes to `logs/spring.log` (relative to its working directory).
