package de.niklas.todo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Todo {

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    public Todo(String description, LocalDate dueDate){
        this.description = description;
        this.dueDate = dueDate;
    }
}
