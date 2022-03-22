# Microservice V3

This project is an example of microservice using Spring Boot, Docker and Kafka.

# Running the application.

### 1. Create the Docker Image

The folder `/scripts` has the scripts to create a populated database MariaDB.

Run the commands below on the root project folder.

```shell
<microserviceV3>$ docker build -t mariadb-custon:latest .
<microserviceV3>$ docker tag mariadb-custon:latest ricardorqr/mariadb-custon
<microserviceV3>$ docker push ricardorqr/mariadb-custon
```

- URL: http://localhost:8080/?server=mariadb
- Server: mariadb
- Username: root
- Password: example
- Database: example

### 2. Run application

1. Run docker

On the root project folder.

```shell
<microserviceV3>$ docker compose up
```