# Parque diversiones API 💰💥
Este repositorio contiene los servicios API REST para la administración de entradas vendidas de los distintos juegos en un parque de diversiones. Incluye gestión de empleados, juegos, horarios, ventas de entradas y generación de reportes. Construida utilizando el framework [Spring Boot](https://spring.io/projects), se desarrollo durante el evento [HackaCode](https://hackacode.todocodeacademy.com/) organizado por TodoCode.

## 💡 Requerimientos
Antes de empezar, asegurémonos de tener instalado lo siguiente:

- [Java 11](https://adoptium.net/es/temurin/releases/) debe ser instalado. Ejecuta los siguientes comandos para verificar la versión:
  ```bash
  java --version
  ```
- [PostgreSQL](https://www.postgresql.org/) debe estar previamente instalado.

## 🚀 Instalación

1. Descargar o clonar el repositorio.
    ```bash
    git clone https://github.com/kacubillos/parque-diversiones-api.git
    ```
2. Configurar las variables de entorno para la base de datos.
   
   - `DATABASE_URL`
   - `DATABASE_USERNAME`
   - `DATABASE_PASSWORD`

   En Windows, abra una terminal y escriba (reemplazando [password] con la contraseña de Postgres):
    
   ```bash
    setx DATABASE_USERNAME "postgres"
    setx DATABASE_PASSWORD "[password]"
    ```
   También, puede reemplazar los valores directamente en el archivo `application-dev.properties`.

    ```bash
    spring.datasource.username=XXXXX
    spring.datasource.password=XXXXX
    ```

3. Iniciar el proyecto en modo desarrollo desde el IDE.
   
   (Al utilizar variables de entorno en Windows debemos ejecutar el IDE como administrador)

4. En el primer inicio debemos registrar la información de los roles y al menos un empleado desde Postgres.
    ```sql
    -- ROLES
    INSERT INTO roles VALUES (1, 'ADMIN');
    INSERT INTO roles VALUES (2, 'EMP_JUEGO');
    
    -- EMPLEADOS
    INSERT INTO empleados (id_empleado, tipo_documento, numero_documento, nombre, apellidos, fecha_nacimiento, id_rol)
        VALUES (1, 'C.C', 100, 'Kevin', 'Cubillos', '2000-10-01', 1);
    
    -- SE REINICIAN LAS SECUENCIAS SEGÚN LOS DATOS INICIALES
    SELECT setval('public.roles_id_rol_seq', 2, true);
    SELECT setval('public.empleados_id_empleado_seq', 3, true);
    ```
   Podemos crear un nuevo usuario para el empleado registrado y acceder a las demás rutas.