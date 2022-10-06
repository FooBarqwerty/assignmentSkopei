dependencies: apache maven, java 17, docker-compose

### 1) spin up the database by running the following command in the project root
```shell
$ docker compose up
```

### 2) build and run the java project
```shell
$ mvn install
$ mvn spring-boot:run
```
### 3) documentation is available at
http://localhost:8080/swagger-ui/index.html#/
