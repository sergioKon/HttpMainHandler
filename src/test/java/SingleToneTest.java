import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SingleToneTest {

    @Test
    public void ApproveHTTPBase() throws URISyntaxException {

        HttpExchange exchange = mock(HttpExchange.class);

        when(exchange.getRequestURI()).thenReturn(new URI("/allow"));

        // Simulate request body
        String requestBody = "key=value&foo=bar";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(requestBody.getBytes());
        when(exchange.getRequestBody()).thenReturn(inputStream);
    }
}
