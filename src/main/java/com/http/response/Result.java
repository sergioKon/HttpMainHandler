package com.http.response;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class Result {
    private final int code;
    private final String message;

    public Result(HttpStatus httpStatus){
         this.code= httpStatus.getCode();
         this.message = httpStatus.getDescription();
    }
    public void send(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(code, message.length());
        exchange.getResponseBody().write(message.getBytes());
        exchange.close();
    }
}
