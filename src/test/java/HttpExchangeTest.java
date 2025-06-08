import com.http.handlers.MainHttpHandler;
import com.http.response.HttpStatus;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import static org.mockito.Mockito.*;

public class HttpExchangeTest {

    Headers headers;
    HttpExchange exchange = mock(HttpExchange.class);

    @BeforeEach
    public  void initTests(){
        headers= new Headers();
        headers.clear();
    }

    @Test
    public void testHttpExchangeBookingHandler() throws Exception {
        // Mock HttpExchange

        headers.add("headerKey", "Allow");
        String body = "name=price&account=100";
        // Mock request body
        String path = "/booking";

        OutputStream os = getOutputStream(path, body, exchange, headers);

        // Verify response headers and output
        verify(exchange).sendResponseHeaders(eq(HttpStatus.OK.getCode()), anyLong());
        verify(os).write(any(byte[].class));
    }

    @Test
    public void testHttpExchangeAllowHandler() throws Exception {
        // Mock HttpExchange

        headers.add("headerKey", "Allow");
        String body = "name=John&age=30";
        // Mock request body
        String path = "/allow";

        OutputStream os = getOutputStream(path, body, exchange, headers);

        // Verify response headers and output
        verify(exchange).sendResponseHeaders(eq(HttpStatus.OK.getCode()), anyLong());
        verify(os).write(any(byte[].class));
    }

    private static OutputStream getOutputStream(String path, String body, HttpExchange exchange, Headers headers) throws URISyntaxException {
        // Mock URI
        when(exchange.getRequestURI()).thenReturn(new URI(path));

        // Mock request body

        ByteArrayInputStream input = new ByteArrayInputStream(body.getBytes());
        when(exchange.getRequestBody()).thenReturn(input);

        // Mock response output stream
        OutputStream os = mock(OutputStream.class);
        when(exchange.getResponseBody()).thenReturn(os);

        when(exchange.getRequestHeaders()).thenReturn(headers);

        // Call handler method (assuming you have a handler)
        MainHttpHandler handler = new MainHttpHandler();
        handler.handle(exchange);
        return os;
    }
}