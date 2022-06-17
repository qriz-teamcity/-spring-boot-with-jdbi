package de.mkammerer.jdbispring.service;

import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.List;

public interface TaskRepository {
    Task createNew(String title, @Nullable ZonedDateTime due);

    List<Task> findAll();
}
