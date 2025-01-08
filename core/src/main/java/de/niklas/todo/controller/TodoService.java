package de.niklas.todo.controller;

import de.niklas.todo.model.Todo;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The TodoService class handles the business logic related to Todo items.
 * It manages a list of Todo items and provides methods for retrieving, adding, and deleting Todos.
 * This service acts as an intermediary between the controller and the data (in this case, an in-memory list).
 */
@Service
public class TodoService {

    @NonNull private final List<Todo> todoList = new ArrayList<>();
    private long currentId = 1;

    /**
     * Retrieves all Todo items from the list.
     *
     * @return a List of all Todo items
     */
    public List<Todo> getAllTodos() {
        return new ArrayList<>(todoList);
    }

    /**
     * Adds a new Todo item to the list and assigns it a unique ID.
     *
     * @param todo the Todo item to be added
     * @return the created Todo item with the assigned ID
     */
    public Todo addTodo(@NonNull Todo todo) {
        todo.setId(currentId++);
        todoList.add(todo);
        return todo;
    }

    /**
     * Deletes a Todo item from the list by its ID.
     *
     * @param id the ID of the Todo item to be deleted
     * @return true if the Todo item was found and deleted, false if the item was not found
     */
    public boolean deleteTodoById(@NonNull Long id) {
        Optional<Todo> todoOptional = todoList.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst();
        if (todoOptional.isPresent()) {
            todoList.remove(todoOptional.get());
            return true;
        }
        return false;
    }
}