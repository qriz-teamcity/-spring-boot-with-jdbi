package de.mkammerer.jdbispring.database;

import de.mkammerer.jdbispring.service.Task;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.ZonedDateTime;
import java.util.List;

interface TaskDao {
    @SqlUpdate("insert into tasks (title, due) values (?, ?)")
    @GetGeneratedKeys
    long insert(String title, ZonedDateTime due);

    @SqlQuery("select * from tasks")
    List<Task> findAll();
}
