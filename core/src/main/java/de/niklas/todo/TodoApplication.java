package de.niklas.todo;

import lombok.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The TodoApplication class is the entry point for the Spring Boot application.
 * It initializes the application and starts the embedded server.
 * <p>
 * Project created by Niklas Petermeier on 19th December 2024.
 */
@SpringBootApplication
public class TodoApplication {

    public static void main(@NonNull String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }
}
