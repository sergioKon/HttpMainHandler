package com.http.handlers;


import com.base.validators.TokenValidator;
import com.http.response.HttpStatus;
import com.http.response.Result;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;


public class ProtectedHandler implements HttpHandler, TokenValidator {
    @Override
    public void handle(HttpExchange httpExchange)  throws IOException {
        boolean valid =TokenValidator.super.validate(httpExchange);
        if(valid) {
            String msg = "{\"message\":\"Access granted to protected resource\"}";
            Result result = new Result(httpExchange, HttpStatus.OK);
            httpExchange.getResponseHeaders().add("Content-Type", "application/json");
            result.setMessage(msg);
            result.send();
        }
    }
}
