package com.base.validators;


import com.base.TokenGenerator;
import com.http.response.Result;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface TokenValidator {
     default boolean validate(HttpExchange exchange) throws IOException {
         String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
         if (authHeader == null || !authHeader.equals("Bearer " + TokenGenerator.INSTANCE.getToken())) {
             String error = "{\"error\":\"Unauthorized\"}";
             Result result = new Result(401,error);
             result.send(exchange);
             return false;
         }
         return true;
     }
}
