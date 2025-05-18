package com.http.handlers;

import com.base.Authorization;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;


public class ProtectedHandler implements HttpHandler, Authorization {
    @Override
    public void handle(HttpExchange exchange)  throws IOException {
        boolean valid =validate(exchange);
        if(valid) {
            String msg = "{\"message\":\"Access granted to protected resource\"}";
            Response response = new Response(200, msg);
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            response.send(exchange);
        }
    }
}
