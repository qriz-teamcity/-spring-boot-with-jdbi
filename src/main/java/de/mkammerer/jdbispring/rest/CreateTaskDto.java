package de.mkammerer.jdbispring.rest;

import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;

@Validated
public record CreateTaskDto(@NotBlank String title, @Nullable @Future ZonedDateTime due) {
}
