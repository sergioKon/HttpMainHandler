package com.http.handlers;

import com.base.Authorization;
import com.http.response.Result;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;


public class ProtectedHandler implements HttpHandler, Authorization {
    @Override
    public void handle(HttpExchange exchange)  throws IOException {
        boolean valid =validate(exchange);
        if(valid) {
            String msg = "{\"message\":\"Access granted to protected resource\"}";
            Result result = new Result(200, msg);
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            result.send(exchange);
        }
    }
}
