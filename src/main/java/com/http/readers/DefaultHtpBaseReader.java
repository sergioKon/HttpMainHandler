package com.http.readers;

import com.http.response.HttpStatus;
import com.http.response.UserResponse;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class DefaultHtpBaseReader extends HttpBaseReader {
    public DefaultHtpBaseReader(HttpExchange httpExchange) {
        super(httpExchange);
    }

    @Override
    public void process() {
        userResponse.setHttpStatus(HttpStatus.NOT_IMPLEMENTED);
    }

    public  void sendResponse(String message)  {
        try (OutputStream os = httpExchange.getResponseBody()) {
            httpExchange.sendResponseHeaders(500, message.getBytes().length);
            os.write(message.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
