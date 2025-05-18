package com.http.handlers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class Response {
    private final int code;
    private final String message;

    public Response(int code , String message){
         this.code=code;
         this.message = message;
    }
    public void send(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(code, message.length());
        exchange.getResponseBody().write(message.getBytes());
        exchange.close();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
