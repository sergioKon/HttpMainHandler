package com.http.response;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class Result {
    private final int code;
    private final String message;

    public Result(int code , String message){
         this.code=code;
         this.message = message;
    }
    public void send(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(code, message.length());
        exchange.getResponseBody().write(message.getBytes());
        exchange.close();
    }
}
