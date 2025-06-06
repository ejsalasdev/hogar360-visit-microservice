# Visit Microservice 📅

> Gestiona horarios y visitas de propiedades inmobiliarias del ecosistema Hogar360

## Quick Start

### 🚀 **Ejecutar el servicio**
```bash
# 1. Prerrequisitos: Java 17+ y Gradle 8+
./gradlew bootRun

# 2. Verificar: http://localhost:8083/actuator/health
# 3. Swagger: http://localhost:8083/swagger-ui.html
```

### ⚙️ **Variables de entorno**
```bash
# Opcional - valores por defecto para desarrollo
SERVER_PORT=8083
PROPERTY_MICROSERVICE_URL=http://localhost:8081
JWT_SECRET=your-secret-key
DB_HOST=localhost
DB_NAME=hogar360_visit_db
```

## API Endpoints

### 🕐 **TimeSlots**
| Método | Endpoint | Descripción | Rol |
|--------|----------|-------------|-----|
| POST | `/api/v1/appointmentslot` | Crear horario | seller |
| GET | `/api/v1/appointmentslot/house/{id}` | Horarios de propiedad | buyer/seller |

### 📋 **Visits**
| Método | Endpoint | Descripción | Rol |
|--------|----------|-------------|-----|
| POST | `/api/v1/visit` | Solicitar visita | buyer |
| GET | `/api/v1/visit/buyer` | Mis visitas | buyer |
| GET | `/api/v1/visit/seller` | Visitas recibidas | seller |

## Tech Stack

- **Spring Boot 3** + **WebFlux** - Framework reactivo
- **Clean Architecture** + **Hexagonal** - Separación de capas
- **JWT** - Autenticación por roles
- **MySQL** - Base de datos
- **OpenFeign** - Comunicación con Property Service
- **Docker** + **GitHub Actions** - CI/CD

## Testing & Build

```bash
# Tests
./gradlew test

# Docker
docker build -t visit-microservice .
docker run -p 8083:8083 visit-microservice
```

## Ecosystem

| Servicio | Puerto | Descripción |
|----------|--------|-------------|
| Property | 8081 | Gestión de propiedades |
| User | 8082 | Autenticación y usuarios |
| **Visit** | **8083** | **Horarios y visitas (este)** |
| Gateway | 8080 | API Gateway |
| Frontend | 4200 | Angular UI |

---

**Spring Boot 3** • **Java 17** • **Clean Architecture** • **Docker Ready**
