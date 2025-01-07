import de.niklas.todo.TodoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TodoApplication.class)
public class WebConfigTest {

    @Value("${local.server.port}")
    private int port;

    @Test
    public void testCORSConfiguration() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:" + port + "/todos";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        HttpHeaders headers = response.getHeaders();

        assertTrue(headers.containsKey("Access-Control-Allow-Origin"), "CORS header missing");

        assertEquals("http://localhost:3000", headers.get("Access-Control-Allow-Origin").get(0));
    }
}