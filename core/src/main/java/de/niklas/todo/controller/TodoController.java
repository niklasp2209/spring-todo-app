package de.niklas.todo.controller;

import de.niklas.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The TodoController class is responsible for handling the HTTP requests related to Todo items.
 * It provides endpoints to add, delete, and retrieve Todo items.
 * The class communicates with the TodoService to manage the business logic.
 */
@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Endpoint to retrieve all Todo items.
     *
     * @return ResponseEntity containing a list of all Todos with an HTTP status of 200 OK
     */
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    /**
     * Endpoint to add a new Todo item.
     * Validates the Todo's title and creates it if valid.
     *
     * @param todo the Todo item to be added
     * @return ResponseEntity containing the created Todo item with an HTTP status of 201 CREATED
     * or 400 BAD REQUEST if the title is invalid
     */
    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        if (todo.getTitle() == null || todo.getTitle().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Todo createdTodo = todoService.addTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    /**
     * Endpoint to delete a Todo item by its ID.
     *
     * @param id the ID of the Todo to be deleted
     * @return ResponseEntity with a message and the HTTP status of 200 OK if deleted successfully,
     * or 404 NOT FOUND if the Todo with the given ID does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        boolean isDeleted = todoService.deleteTodoById(id);
        if (isDeleted) {
            return ResponseEntity.ok("TODO with ID " + id + " deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TODO not found");
    }

}