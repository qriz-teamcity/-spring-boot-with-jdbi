package de.mkammerer.jdbispring.service;

import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;

public record Task(long id, String title, @Nullable ZonedDateTime due) {
}
