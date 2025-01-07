package de.niklas.todo;

import lombok.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication {

    public static void main(@NonNull String[] args){
        SpringApplication.run(TodoApplication.class, args);
    }
}
