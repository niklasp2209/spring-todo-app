import de.niklas.todo.model.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class to verify the functionality of the Todo class, including the creation of Todo objects,
 * setter and getter methods, and validation of non-null constructor parameters.
 */
public class TodoTest {

    /**
     * This test case verifies the correct creation of a Todo object.
     * It checks that the description and due date are properly set in the Todo object.
     */
    @Test
    public void testTodoCreation() {
        LocalDate dueDate = LocalDate.of(2025, 1, 10);
        String description = "Finish project";

        Todo todo = new Todo(description, dueDate);

        assertNotNull(todo, "Todo object should not be null");
        assertEquals(description, todo.getDescription(), "Description should match");
        assertEquals(dueDate, todo.getDueDate(), "Due date should match");
    }

    /**
     * This test case verifies the functionality of the setter and getter methods in the Todo class.
     * It checks that the values set through the setters match the expected values when retrieved using getters.
     */
    @Test
    public void testSettersAndGetters() {
        Todo todo = new Todo("Initial task", LocalDate.of(2025, 1, 10));

        todo.setId(1L);
        todo.setTitle("New Title");
        todo.setDescription("Updated task description");
        todo.setDueDate(LocalDate.of(2025, 2, 15));

        assertEquals(1L, todo.getId(), "ID should match");
        assertEquals("New Title", todo.getTitle(), "Title should match");
        assertEquals("Updated task description", todo.getDescription(), "Description should match");
        assertEquals(LocalDate.of(2025, 2, 15), todo.getDueDate(), "Due date should match");
    }

    /**
     * This test case verifies that the Todo class's constructor throws a NullPointerException
     * if either the description or due date is null.
     */
    @Test
    public void testNonNullConstructorParameters() {
        LocalDate dueDate = LocalDate.of(2025, 1, 10);

        assertThrows(NullPointerException.class, () -> new Todo(null, dueDate), "Description should not be null");
        assertThrows(NullPointerException.class, () -> new Todo("Valid description", null), "Due date should not be null");
    }
}
