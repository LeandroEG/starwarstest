# Star Wars API 

Este proyecto es una aplicación que importa datos de la API de Star Wars (SWAPI) y los almacena en una base de datos relacional en memoria (H2). La aplicación se encarga de importar las películas, 
los personajes y las naves de Star Wars utilizando llamadas a la API de SWAPI y mapeando los datos en entidades de la base de datos, realizando posteriormente diversas
consulta sobre esos datos

## Características

- Importación de películas, personajes y naves de Star Wars.
- Obtener el listado de todas las películas
- Obtener el listado de todos los personajes, indicando el número de películas en las que ha aparecido y el título de las mismas
- Obtener el personaje que ha pilotado la nave que más veces aparece en un listado de películas determinadas.

## Tecnologías utilizadas

- Java
- Spring Boot
- Spring Data JPA
- RestTemplate

## Requisitos previos

- Java JDK 8 o superior
- Gradle

## Instalación y configuración

1. Clona el repositorio o descarga el código fuente.
2. Abre el proyecto en tu entorno de desarrollo preferido.
3. Configura la conexión a la base de datos en el archivo `application.yaml`.
4. Ejecuta la aplicación desde tu entorno de desarrollo o utiliza el comando `mvn spring-boot:run`.

## Uso

La aplicación se ejecuta como una aplicación de Spring Boot. Al arrancar, la aplicación realiza llamadas a la API de Star Wars para importar los datos de las películas, 
los personajes y las naves. Los datos se almacenan en la base de datos configurada.

Una vez arrancado el servidor quedarán expuestos varios servicios.
Para consumir dichos servicios se puede utilizar:
   - Postman para peticiones Rest
   - Swagger UI: Accediendo desde el navegador a la siguiente URL: http://localhost:8080/swagger-ui/index.html

## Autor

Leandro Espinosa García

