package de.mkammerer.jdbispring.database;

import de.mkammerer.jdbispring.service.Task;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

/**
 * Note the {@link Component} annotation, which makes this automatically available to JDBI.
 */
@Component
class TaskRowMapper implements RowMapper<Task> {
    @Override
    public Task map(ResultSet rs, StatementContext ctx) throws SQLException {
        long id = rs.getLong("id");
        String title = rs.getString("title");
        ZonedDateTime due = rs.getObject("due", ZonedDateTime.class);

        return new Task(id, title, due);
    }
}
