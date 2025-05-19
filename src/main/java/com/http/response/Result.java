package com.http.response;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class Result {
    private final int code;
    private String message;
    private final HttpExchange httpExchange;

    public Result(HttpExchange httpExchange, HttpStatus httpStatus){
         this.code= httpStatus.getCode();
         this.message = httpStatus.getDescription();
         this.httpExchange= httpExchange;
    }
    public void  setMessage(String message){
        this.message= message;
    }
    public void send() throws IOException {
        httpExchange.sendResponseHeaders(code, message.length());
        httpExchange.getResponseBody().write(message.getBytes());
        try (OutputStream os = httpExchange.getResponseBody()) {
            message+="\n";
            os.write(message.getBytes());

        }
        httpExchange.close();
    }
}
