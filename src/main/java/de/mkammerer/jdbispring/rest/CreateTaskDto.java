package de.mkammerer.jdbispring.rest;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import java.time.ZonedDateTime;

@Validated
public record CreateTaskDto(@NotBlank String title, @Nullable @Future ZonedDateTime due) {
}
