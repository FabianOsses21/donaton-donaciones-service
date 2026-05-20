# donaciones-service DTO + Liquibase

Microservicio de gestion de donaciones para Donaton.

## Ejecutar

```bash
mvn clean install
mvn spring-boot:run
```

Puerto:

```txt
8082
```

## Base de datos

Este proyecto usa Liquibase. Ya no usa `ddl-auto=update`.

```properties
spring.jpa.hibernate.ddl-auto=none
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml
```

Base de datos:

```txt
donaton_donaciones
```

## Crear donacion

```http
POST http://localhost:8082/api/v1/donaciones
```

```json
{
  "tipoRecurso": "ALIMENTO",
  "detalleRecurso": "Cajas de mercaderia",
  "cantidad": 50,
  "origen": "Empresa privada",
  "nombreDonante": "Supermercado Solidario",
  "contactoDonante": "contacto@empresa.cl",
  "centroAcopioId": 1,
  "necesidadId": 1
}
```
