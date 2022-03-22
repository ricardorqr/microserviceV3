FROM mariadb:latest

ENV MARIADB_DATABASE example

COPY /scripts/CREATE.sql /docker-entrypoint-initdb.d/
COPY /scripts/INSERT.sql /docker-entrypoint-initdb.d/