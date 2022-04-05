# Microservice V3

This project is an example of microservice using Spring Boot, Docker and Kafka.

# Running the application.

### 1. Create the MariaDB Docker Image

The folder `/scripts` has the scripts to create and populate the MariaDB database.

Run the commands below on the root project folder at the same order.

```shell
<microserviceV3>$ docker build -t mariadb-custon:latest .
<microserviceV3>$ docker tag mariadb-custon:latest ricardorqr/mariadb-custon
<microserviceV3>$ docker push ricardorqr/mariadb-custon
```

Here is how to check the database

- URL: [http://localhost:8080/?server=mariadb](http://localhost:8080/?server=mariadb)
- Server: mariadb
- Username: root
- Password: example
- Database: example

![Table Customer](files/MariaDB01.png)

### 2. Run application

1. Run docker

In the root project folder, run the command:

```shell
<microserviceV3>$ docker compose up
```

2. Run Eureka Server

In the `eureka-server` project folder, run the command:

```shell
<eureka-server>$ mvn spring-boot:run
```

3. Run All Services

In each service folder (`service-customer`, `service-fraud` and `service-notification`) without caring about the order, run the command:

```shell
<each-project-folder>$ mvn spring-boot:run
```

3. Postman

- POST: [http://localhost:8081/customers/](http://localhost:8081/customers/)
- JSON:

```JSON
{
  "firstName": "Ricardo",
  "lastName": "Ribeiro",
  "email": "ricardo@gmail.com"
}
```

### 3. Links

- Eureka Server: [http://localhost:8761](http://localhost:8761)
- Customer MS Swagger: [http://localhost:8081/customers/swagger](http://localhost:8081/customers/swagger)
- Fraud MS Swagger: [http://localhost:8082/fraud/swagger](http://localhost:8082/customers/swagger)

# Resources

- https://www.youtube.com/watch?v=NjHYWEV_E_o
- https://github.com/TechPrimers/spring-boot-kafka-producer-example
- https://www.youtube.com/watch?v=IncG0_XSSBg&t=285s
- https://github.com/TechPrimers/spring-boot-kafka-consumer-example