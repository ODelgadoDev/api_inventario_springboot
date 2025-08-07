# API REST para Gestión de Inventario
# API creada por Jesus Orlando Delgado Azar
Este proyecto es una **API REST** desarrollada en **Spring Boot 3.5.0** con persistencia en base de datos **SQL Server**, que permite gestionar un inventario para una tienda de tipo minisúper.

Permite realizar operaciones CRUD sobre:

- **Usuarios**
- **Productos**
- **Entradas** de inventario
- **Salidas** de inventario

También permite registrar las entradas y salidas de productos actualizando automáticamente el stock disponible.

## Tecnologías utilizadas

- **Java 22**
- **Spring Boot 3.5.0**
- **Spring Data JPA**
- **Spring Security**
- **SQL Server**
- **Postman** (para pruebas)
- **Git / GitHub**

## Endpoints disponibles

### Usuarios
- POST `/api/auth/register` → Registrar usuario
- POST `/api/auth/login` → Login de usuario

### Productos
- GET `/api/productos` → Listar productos
- GET `/api/productos/{id}` → Consultar producto por ID
- POST `/api/productos` → Agregar producto
- PUT `/api/productos/{id}` → Actualizar producto
- DELETE `/api/productos/{id}` → Eliminar producto

### Entradas
- GET `/api/entradas` → Listar entradas
- GET `/api/entradas/{id}` → Consultar entrada por ID
- POST `/api/entradas` → Registrar entrada

### Salidas
- GET `/api/salidas` → Listar salidas
- GET `/api/salidas/{id}` → Consultar salida por ID
- POST `/api/salidas` → Registrar salida

## Instalación y ejecución

1. Clona este repositorio:
   ```bash
   git clone git@github.com:ODelgadoDev/api_inventario_springboot.git
   
final