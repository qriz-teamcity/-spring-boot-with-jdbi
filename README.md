# Spring Boot with JDBI

[![Java CI](https://github.com/phxql/spring-boot-with-jdbi/actions/workflows/build.yaml/badge.svg)](https://github.com/phxql/spring-boot-with-jdbi/actions/workflows/build.yaml)

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

* `curl -X POST -d '{"due":"2023-01-01T00:00:00Z","title":"bar"}' http://localhost:8080/task -s -S -v -H "Content-Type: application/json" -H "Accept: application/json, */*"`
* This is handled by the [TaskController](src/main/java/de/mkammerer/jdbispring/rest/TaskController.java)

## JDBI Configuration

The [JdbiConfiguration](src/main/java/de/mkammerer/jdbispring/database/JdbiConfiguration.java) creates 3 beans:

* The `Jdbi` bean, which is used by the `TaskDao` bean. The `Jdbi` bean uses the `TransactionAwareDataSourceProxy` to
  be aware of the Spring managed transactions. Additionally, it picks up all registered `JdbiPlugin`s and `RowMapper`s
  beans and registers them on the JDBI system.
* The `SqlObjectPlugin` bean. This bean will then be picked up by the `Jdbi` bean to be registered on the JDBI system.
  The `SqlObjectPlugin` allows the use of the DAO interfaces, see
  the [`TaskDao`](src/main/java/de/mkammerer/jdbispring/database/TaskDao.java)
  for an example.
* The `TaskDao` bean, which is used by
  the [`JdbiTaskRepository`](src/main/java/de/mkammerer/jdbispring/database/JdbiTaskRepository.java)
  to do the database access through JDBI.
* Note: There's an additional bean which is used by JDBI:
  the [`TaskRowMapper`](src/main/java/de/mkammerer/jdbispring/database/TaskRowMapper.java).
  This bean is not created via a `@Bean` method but is registered as a bean via the `@Component` annotation. As this
  bean
  implements `RowMapper`, it is used by the `Jdbi` bean and is registered in the JDBI system. This bean is used to
  convert JDBC records to `Task` objects, which is needed by the `TaskDao` bean.

## License

[CC0](https://creativecommons.org/publicdomain/zero/1.0/deed.en)
