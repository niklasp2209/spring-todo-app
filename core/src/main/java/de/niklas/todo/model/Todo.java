package de.niklas.todo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

/**
 * The Todo class represents a task or to-do item.
 * It contains the task's details, such as its title, description, due date, and a unique ID.
 * This class is used to store and transfer the data related to each Todo item.
 */
@Getter
@Setter
public class Todo {

    private Long id;
    private String title;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    public Todo(@NonNull String description, @NonNull LocalDate dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }
}
