package com.http.handlers;


import com.base.LoginUser;
import com.base.TokenGenerator;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LoginHandler implements HttpHandler {
    static final ObjectMapper mapper = new ObjectMapper();
    private final Logger LOGGER= LogManager.getLogger(LoginHandler.class);
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        InputStream bodyStream = exchange.getRequestBody();

        String json = new String( bodyStream.readAllBytes());
        ObjectMapper mapper = new ObjectMapper();
        LoginUser loginUser = mapper.readValue(json, LoginUser.class);

        ObjectNode requestJson = (ObjectNode) mapper.readTree( exchange.getRequestBody());
        String username = requestJson.get("username").asText();
        String password = requestJson.get("password").asText();

        if ("admin".equals(username) && "secret".equals(password)) {
            ObjectNode responseJson = mapper.createObjectNode();
            TokenGenerator tokenGenerator = TokenGenerator.INSTANCE;
            tokenGenerator.generate();

            LOGGER.info(" token = {}", tokenGenerator.getToken());
            responseJson.put("token", tokenGenerator.getToken());

            byte[] responseBytes = mapper.writeValueAsBytes(responseJson);
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, responseBytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseBytes);
            }
        } else {
            String error = "{\"error\":\"Invalid credentials\"}";
            exchange.sendResponseHeaders(401, error.length());
            exchange.getResponseBody().write(error.getBytes());
            exchange.close();
        }
    }
}
