package com.http.handlers;

import com.base.TokenGenerator;
import com.base.validators.LoginValidator;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.http.response.HttpStatus;
import com.http.response.Result;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

public class LoginHandler implements HttpHandler, LoginValidator {
    private final Logger LOGGER= LogManager.getLogger(LoginHandler.class);
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        try {
            LoginValidator.super.validate(httpExchange);
            TokenGenerator tokenGenerator = TokenGenerator.INSTANCE;
            tokenGenerator.generate();
            String token = tokenGenerator.getToken();
            LOGGER.info(" token = {}", token);
            httpExchange.getResponseHeaders().add("Content-Type", "application/json");
            Result result = new Result(httpExchange, HttpStatus.OK);
            result.setMessage(" token =  " + token);
            result.send();
       }
        catch (UnrecognizedPropertyException e) {
            Result result = new Result(httpExchange, HttpStatus.BAD_REQUEST);
            result.setMessage(e.getMessage());
            result.send();
        }
    }
}
