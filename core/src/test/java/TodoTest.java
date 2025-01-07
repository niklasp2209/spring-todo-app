
import de.niklas.todo.model.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TodoTest {

    @Test
    public void testTodoCreation() {
        LocalDate dueDate = LocalDate.of(2025, 1, 10);
        Todo todo = new Todo("Finish project", dueDate);

        assertNotNull(todo);
        assertEquals("Finish project", todo.getDescription());
        assertEquals(dueDate, todo.getDueDate());
    }

    @Test
    public void testSettersAndGetters() {
        Todo todo = new Todo("Finish project", LocalDate.of(2025, 1, 10));

        todo.setDescription("New task");
        todo.setDueDate(LocalDate.of(2025, 2, 15));

        assertEquals("New task", todo.getDescription());
        assertEquals(LocalDate.of(2025, 2, 15), todo.getDueDate());
    }
}