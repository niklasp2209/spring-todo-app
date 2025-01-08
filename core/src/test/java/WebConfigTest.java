import de.niklas.todo.TodoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Unit test class to verify the CORS (Cross-Origin Resource Sharing) configuration of the application.
 * This test ensures that the appropriate CORS headers are set in the response for requests from allowed origins.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TodoApplication.class)
public class WebConfigTest {

    @Autowired
    private WebTestClient webTestClient;

    /**
     * This test case verifies that the CORS configuration correctly allows requests from the specified origin.
     * It sends a GET request to the "/todos" endpoint with a custom "Origin" header and verifies that the response
     * contains the expected "Access-Control-Allow-Origin" header with the allowed origin.
     */
    @Test
    public void testCORSConfiguration() {
        webTestClient.get()
                .uri("/todos")
                .header("Origin", "http://localhost:3000")
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .valueEquals("Access-Control-Allow-Origin", "http://localhost:3000");
    }
}