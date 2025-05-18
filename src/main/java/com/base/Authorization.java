package com.base;

import com.http.handlers.Response;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface Authorization {
     default boolean validate(HttpExchange exchange) throws IOException {
         String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
         if (authHeader == null || !authHeader.equals("Bearer " + TokenGenerator.INSTANCE.getToken())) {
             String error = "{\"error\":\"Unauthorized\"}";
             Response response= new Response(401,error);
             response.send(exchange);
             return false;
         }
         return true;
     }
}
