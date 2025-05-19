package com.http.handlers;


import com.base.LoginUser;
import com.base.TokenGenerator;
import com.base.validators.LoginValidator;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.http.response.HttpStatus;
import com.http.response.Result;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LoginHandler implements HttpHandler, LoginValidator {
    static final ObjectMapper mapper = new ObjectMapper();
    private final Logger LOGGER= LogManager.getLogger(LoginHandler.class);
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        LoginValidator.super.validate(exchange);
        ObjectNode responseJson = mapper.createObjectNode();
        TokenGenerator tokenGenerator = TokenGenerator.INSTANCE;
        tokenGenerator.generate();

        LOGGER.info(" token = {}", tokenGenerator.getToken());
        responseJson.put("token", tokenGenerator.getToken());
        Result result = new Result(HttpStatus.OK);
        byte[] responseBytes = mapper.writeValueAsBytes(responseJson);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, responseBytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseBytes);
        }
    }
}
