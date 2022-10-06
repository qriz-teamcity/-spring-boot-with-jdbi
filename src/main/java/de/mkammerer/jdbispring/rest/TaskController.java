package de.mkammerer.jdbispring.rest;

import de.mkammerer.jdbispring.service.Task;
import de.mkammerer.jdbispring.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDto> findAll() {
        return taskService.findAll().stream()
                .map(TaskDto::fromTask)
                .toList();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto create(@Validated @RequestBody CreateTaskDto dto) {
        Task created = taskService.createNew(dto.title(), dto.due());
        return TaskDto.fromTask(created);
    }
}
