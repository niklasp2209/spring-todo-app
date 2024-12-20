package de.niklas.todo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/todos")
public class TodoController {

    private List<Todo> todoList = new ArrayList<>();

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoList;
    }

    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        todoList.add(todo);
        return todo;
    }

    @DeleteMapping("/{index}")
    public String deleteTodo(@PathVariable int index) {
        if (index >= 0 && index < todoList.size()) {
            todoList.remove(index);
            return "TODO at index " + index + " deleted";
        }
        return "TODO not found";
    }
}