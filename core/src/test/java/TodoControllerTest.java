import de.niklas.todo.TodoApplication;
import de.niklas.todo.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TodoApplication.class)
public class TodoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void testAddTodo() {
        Todo todo = new Todo("Finish project", LocalDate.of(2025, 1, 10));

        webTestClient.post()
                .uri("/todos")
                .bodyValue(todo)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Todo.class)
                .consumeWith(response -> {
                    Todo responseBody = response.getResponseBody();
                    assertNotNull(responseBody);
                    assertEquals(todo.getDescription(), responseBody.getDescription());
                    assertEquals(todo.getDueDate(), responseBody.getDueDate());
                });
    }

    @Test
    public void testGetAllTodos() {
        Todo todo = new Todo("Finish project", LocalDate.of(2025, 1, 10));
        webTestClient.post()
                .uri("/todos")
                .bodyValue(todo)
                .exchange()
                .expectStatus().isOk();

        webTestClient.get()
                .uri("/todos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Todo.class)
                .hasSize(1);
    }

    @Test
    public void testDeleteTodo() {
        Todo todo = new Todo("Finish project", LocalDate.of(2025, 1, 10));
        webTestClient.post()
                .uri("/todos")
                .bodyValue(todo)
                .exchange()
                .expectStatus().isOk();

        webTestClient.delete()
                .uri("/todos/0")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("TODO at index 0 deleted");
    }

    @Test
    public void testDeleteTodo_NotFound() {
        webTestClient.delete()
                .uri("/todos/999")
                .exchange()
                .expectStatus().isNotFound();
    }
}