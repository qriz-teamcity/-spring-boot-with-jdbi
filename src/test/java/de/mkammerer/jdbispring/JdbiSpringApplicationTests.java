package de.mkammerer.jdbispring;

import de.mkammerer.jdbispring.rest.CreateTaskDto;
import de.mkammerer.jdbispring.rest.TaskDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JdbiSpringApplicationTests {

	private TestRestTemplate restTemplate;

	@BeforeEach
	void setUp(@LocalServerPort int serverPort) {
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder().rootUri(
				"http://localhost:%d/".formatted(serverPort)
		);
		restTemplate = new TestRestTemplate(restTemplateBuilder);
	}

	@Test
	void test() {
		// Check for initial task
		ResponseEntity<TaskDto[]> tasks = restTemplate.getForEntity("/task", TaskDto[].class);
		assertThat(tasks.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(tasks.getBody()).containsExactly(
				new TaskDto(1, "Understand this code", "2022-10-07T11:44:49.900631+02:00")
		);

		// Create a new task
		CreateTaskDto body = new CreateTaskDto("Test title", null);
		ResponseEntity<TaskDto> created = restTemplate.postForEntity("/task", body, TaskDto.class);
		assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(created.getBody()).isEqualTo(new TaskDto(2, "Test title", null));

		// Check that we can find the created task
		tasks = restTemplate.getForEntity("/task", TaskDto[].class);
		assertThat(tasks.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(tasks.getBody()).hasSize(2);
		assertThat(tasks.getBody()[1]).isEqualTo(new TaskDto(2, "Test title", null));
	}
}
