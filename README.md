📦 Proyecto Final - API REST con Spring Boot
Este proyecto es una API REST desarrollada en Spring Boot, que incluye documentación automática con Swagger / OpenAPI. La aplicación permite gestionar [describir brevemente el propósito, por ejemplo: "usuarios y operaciones bancarias", "productos e inventarios", etc.].

🚀 Tecnologías utilizadas
Java 17+

Spring Boot 3.2.x

Spring Web

Spring Security

Spring Data JPA

Springdoc OpenAPI 2.5.0 (para documentación Swagger)

Base de datos: [Ejemplo: H2, PostgreSQL, MySQL, etc.]

Maven

⚙️ Configuración del proyecto
1. Clonar el repositorio
git clone https://github.com/usuario/proyecto-final.git
cd proyecto-final

2. Configurar las variables de entorno
Podés crear un archivo .env o configurar las variables directamente:
spring.application.name=ProjectFinal
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/ProjectFinal_db?useSSL=false&serverTimezone=America/Mexico_City&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=
spring.jpa.generate-ddl=false
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
spring.jpa.open-in-view=false
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html

3. Construir el proyecto

./mvnw clean install
4. Ejecutar la aplicación

./mvnw spring-boot:run
La API estará disponible en:

http://localhost:8080
📑 Acceso a Swagger UI
Una vez levantado el proyecto, accedé a la documentación Swagger desde:

http://localhost:8080/swagger-ui/index.html

La definición OpenAPI en JSON estará disponible en:

http://localhost:8080/v3/api-docs

🔑 Seguridad
⚠️ La API está protegida por Spring Security, por lo que es posible que requiera autenticación. Podés configurar los usuarios y roles en el archivo application.properties

📂 Estructura del proyecto
src/main/java/com/tuempresa/proyectofinal
├── controller      -> Controladores REST
├── service         -> Lógica de negocio
├── repository      -> Acceso a base de datos
├── entity          -> Modelos de datos (JPA)
└── config          -> Configuración general (Swagger, Security, etc.)

🔍 Autor
Nombre: Martin Paliza Sanchez
GitHub: martinps
