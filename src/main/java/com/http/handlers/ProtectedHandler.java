package com.http.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import static com.http.handlers.LoginHandler.VALID_TOKEN;

public class ProtectedHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String authHeader = exchange.getRequestHeaders().getFirst("Authorization");

        if (authHeader == null || !authHeader.equals("Bearer " + VALID_TOKEN)) {
            String error = "{\"error\":\"Unauthorized\"}";
            exchange.sendResponseHeaders(401, error.length());
            exchange.getResponseBody().write(error.getBytes());
            exchange.close();
            return;
        }

        String response = "{\"message\":\"Access granted to protected resource\"}";
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.close();
    }
}
