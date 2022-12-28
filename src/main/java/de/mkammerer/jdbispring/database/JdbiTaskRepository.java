package de.mkammerer.jdbispring.database;

import de.mkammerer.jdbispring.service.Task;
import de.mkammerer.jdbispring.service.TaskRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
class JdbiTaskRepository implements TaskRepository {
    private final TaskDao taskDao;

    public JdbiTaskRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public Task createNew(String title, ZonedDateTime due) {
        long id = taskDao.insert(title, due);
        return new Task(Task.Id.of(id), title, due);
    }

    @Override
    public List<Task> findAll() {
        return taskDao.findAll();
    }
}
