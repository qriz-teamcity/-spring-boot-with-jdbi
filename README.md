# Spring Boot with JDBI

An example project which showcases Spring Boot with JDBI.

* Database migrations are in [src/main/resources/db/migration](src/main/resources/db/migration) and are executed through
  Flyway
* JDBI Spring configuration is
  in [JdbiConfiguration](src/main/java/de/mkammerer/jdbispring/database/JdbiConfiguration.java)

## Running

* `./mvnw spring-boot:run` OR
* start the `main` method of `de.mkammerer.jdbispring.JdbiSpringApplication` in your IDE

## Find all tasks

* `curl http://localhost:8080/task -s -S -v -H "Accept: application/json, */*"`
* This is handled by the [TaskController](src/main/java/de/mkammerer/jdbispring/rest/TaskController.java)

## Create a task

* `curl -X POST -d {"due":"2023-01-01T00:00:00Z","title":"bar"} http://localhost:8080/task -s -S -v -H "Content-Type: application/json" -H "Accept: application/json, */*"`
* This is handled by the [TaskController](src/main/java/de/mkammerer/jdbispring/rest/TaskController.java)

## License

[CC0](https://creativecommons.org/publicdomain/zero/1.0/deed.en)
