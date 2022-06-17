package de.mkammerer.jdbispring.service;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Task createNew(String title, @Nullable ZonedDateTime due) {
        return this.taskRepository.createNew(title, due);
    }

    @Transactional(readOnly = true)
    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }
}
