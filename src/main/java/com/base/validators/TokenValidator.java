package com.base.validators;


import com.base.TokenGenerator;
import com.http.response.HttpStatus;
import com.http.response.Result;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface TokenValidator {
     default boolean validate(HttpExchange httpExchange) throws IOException {
         String authHeader = httpExchange.getRequestHeaders().getFirst("Authorization");
         if (authHeader == null || !authHeader.equals("Bearer " + TokenGenerator.INSTANCE.getToken())) {
             Result result = new Result(httpExchange, HttpStatus.UNAUTHORIZED);
             result.send();
             return false;
         }
         return true;
     }
}
