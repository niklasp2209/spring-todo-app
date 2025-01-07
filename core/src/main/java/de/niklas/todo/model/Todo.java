package de.niklas.todo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Todo {

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    public Todo(@NonNull String description, @NonNull LocalDate dueDate){
        this.description = description;
        this.dueDate = dueDate;
    }
}
