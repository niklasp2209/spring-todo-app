package de.niklas.todo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig class provides custom configuration for handling Cross-Origin Resource Sharing (CORS)
 * in the Todo application. This configuration allows the frontend to make requests to the backend
 * from a specific domain and enables the necessary HTTP methods and headers.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures CORS mapping for the application.
     *
     * @param registry the CorsRegistry that allows configuring the CORS settings
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}