package de.niklas.todo.controller;

import de.niklas.todo.model.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class TodoController {

    private final List<Todo> todoList = new ArrayList<>();

    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return todoList;
    }

    @PostMapping("/todos")
    public Todo addTodo(@RequestBody Todo todo) {
        todoList.add(todo);
        return todo;
    }

    @DeleteMapping("/todos/{index}")
    public ResponseEntity<String> deleteTodo(@PathVariable int index) {
        if (index >= 0 && index < todoList.size()) {
            todoList.remove(index);
            return ResponseEntity.ok("TODO at index " + index + " deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TODO not found");
    }
}
