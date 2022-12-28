package de.mkammerer.jdbispring.service;

import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;

public record Task(Id id, String title, @Nullable ZonedDateTime due) {
    public record Id(long id) {
        public static Id of(long id) {
            return new Id(id);
        }
    }
}
