package de.mkammerer.jdbispring.rest;

import de.mkammerer.jdbispring.service.Task;
import org.springframework.lang.Nullable;

import java.time.format.DateTimeFormatter;

public record TaskDto(long id, String title, @Nullable String due) {
    public static TaskDto fromTask(Task task) {
        return new TaskDto(task.id().id(), task.title(), task.due() == null ? null : task.due().format(DateTimeFormatter.ISO_DATE_TIME));
    }
}
