# Proyecto de Foro

Este proyecto proporciona una API para gestionar usuarios y tópicos utilizando autenticación JWT.

## Funcionalidades Disponibles

### Registro de Usuario

Permite registrar un nuevo usuario con email y password.

- **Endpoint**: `POST /user/registrar`
- **Body de Ejemplo**:
  ```json
  {
    "email": "usuario@example.com",
    "password": "contraseña"
  }
    ```

## Login de Usuario
Permite autenticar y obtener un token JWT para operaciones protegidas.

- Endpoint: POST /user/login
- Respuesta Exitosa:
```json
{
  "jwtToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmZXJuYW5kbzIiwiaXNzIjoiZm9ybyIsImlkIjozfQ.XXXX.XXXX"
}
```

## Listado de Tópicos
Obtiene la lista de todos los tópicos disponibles.

- Endpoint: GET /topicos?size=2&page=1
- Respuesta de Ejemplo:
```json
{
  "totalPages": 3,
  "totalElements": 5,
  "size": 2,
  "content": [
    {
      "id": 4,
      "titulo": "Hola munod 4",
      "mensaje": "Algun mensaje 4",
      "fechaCreacion": "2024-07-15T06:22:33.419+00:00"
    },
    {
      "id": 5,
      "titulo": "Hola munod 6",
      "mensaje": null,
      "fechaCreacion": "2024-07-15T07:04:50.557+00:00"
    }
  ],
  "number": 1,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "first": false,
  "last": false,
  "numberOfElements": 2,
  "pageable": {
    "pageNumber": 1,
    "pageSize": 2,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 2,
    "paged": true,
    "unpaged": false
  },
  "empty": false
}
```

## Obtener Detalle de Tópico
Obtiene los detalles de un tópico específico por su ID.

- Endpoint: GET /topicos/{id}
- Respuesta de Ejemplo
```json
{
  "id": 2,
  "titulo": "Hola munod 5555",
  "mensaje": "Algun mensaje 5555",
  "fechaCreacion": "2024-07-15T06:22:25.860+00:00"
}
```
## Crear Tópico
Crea un nuevo tópico.

- Endpoint: POST /topicos
- Body de Ejemplo (con Bearer Token en Cabecera):
```json
{
    "titulo": "Hola munod 7",
    "mensaje": "Algun mensaje 7"
}
```


## Actualizar Tópico
Actualiza un tópico existente por su ID.

- Endpoint: PUT /topicos/{id}
- Body de Ejemplo (con Bearer Token en Cabecera):
```json
{
    "titulo": "Nuevo título",
    "mensaje": "Mensaje actualizado"
}
```

## Eliminar Tópico
Elimina un tópico existente por su ID.

- Endpoint: DELETE /topicos/{id}
- Requiere Bearer Token en Cabecera

## Autenticación y Autorización
La autenticación se gestiona utilizando JSON Web Tokens (JWT). Es necesario incluir el token JWT en la cabecera Authorization con el prefijo Bearer para las operaciones protegidas.

## Tecnologías Utilizadas
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (JSON Web Tokens)
- MySQL
