import de.niklas.todo.TodoApplication;
import de.niklas.todo.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This class contains unit tests for the TodoController to ensure proper functionality
 * of adding, retrieving, and deleting Todo items.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TodoApplication.class)
public class TodoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    /**
     * This test case validates the functionality of adding a new Todo.
     * It sends a POST request with a Todo object and checks whether the response contains
     * the created Todo with a matching description, due date, and a non-null ID.
     */
    @Test
    public void testAddTodo() {
        Todo todo = new Todo("Finish project", LocalDate.of(2025, 1, 10));
        todo.setTitle("Important Task");

        webTestClient.post()
                .uri("/todos")
                .bodyValue(todo)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Todo.class)
                .consumeWith(response -> {
                    Todo responseBody = response.getResponseBody();
                    assertNotNull(responseBody);
                    assertEquals(todo.getTitle(), responseBody.getTitle());
                    assertEquals(todo.getDescription(), responseBody.getDescription());
                    assertEquals(todo.getDueDate(), responseBody.getDueDate());
                    assertNotNull(responseBody.getId());
                });
    }

    /**
     * This test case validates retrieving all Todos.
     * It sends a GET request and checks if the response status is 200 (OK)
     * and if the response body contains a list of Todo objects with the expected size.
     */
    @Test
    public void testGetAllTodos() {
        webTestClient.get()
                .uri("/todos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Todo.class)
                .consumeWith(response -> {
                    assertNotNull(response.getResponseBody());
                    assertEquals(1, response.getResponseBody().size());
                });
    }

    /**
     * This test case validates the deletion of a Todo.
     * It first creates a Todo, sends a DELETE request to remove it, and verifies that the response status is 200 (OK).
     */
    @Test
    public void testDeleteTodo() {
        Todo todo = new Todo("Task to delete", LocalDate.of(2025, 3, 15));
        todo.setTitle("Deletable Task");

        webTestClient.post()
                .uri("/todos")
                .bodyValue(todo)
                .exchange()
                .expectStatus().isCreated();

        webTestClient.delete()
                .uri("/todos/1")
                .exchange()
                .expectStatus()
                .isEqualTo(200);
    }

    /**
     * This test case validates the behavior when trying to delete a non-existing Todo.
     * It sends a DELETE request with a non-existent Todo ID and verifies that the response status is 404 (Not Found).
     */
    @Test
    public void testDeleteTodo_NotFound() {
        webTestClient.delete()
                .uri("/todos/999")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class)
                .isEqualTo("TODO not found");
    }
}