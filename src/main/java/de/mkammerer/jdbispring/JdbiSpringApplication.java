package de.mkammerer.jdbispring;

import de.mkammerer.jdbispring.service.Task;
import de.mkammerer.jdbispring.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class JdbiSpringApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(JdbiSpringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JdbiSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner createInitialTasks(TaskService taskService) {
		return args -> {
			Task task = taskService.createNew("Understand this code", ZonedDateTime.now().plusDays(1));
			LOGGER.debug("Created task {}", task);
		};
	}
}
