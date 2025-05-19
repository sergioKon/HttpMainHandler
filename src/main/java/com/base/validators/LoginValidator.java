package com.base.validators;

import com.base.Init;
import com.base.LoginUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.http.response.HttpStatus;
import com.sun.net.httpserver.HttpExchange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public interface LoginValidator {
    Logger logger = LogManager.getLogger(LoginValidator.class);
    default void validate(HttpExchange exchange){

        try (InputStream bodyStream = exchange.getRequestBody()){;
            ObjectMapper  mapper = new ObjectMapper();
            String json = new String( bodyStream.readAllBytes());
            LoginUser loginUser = mapper.readValue(json, LoginUser.class);
            String user = Init.props.get("user").toString();
            String password = Init.props.get("password").toString();
            if(loginUser.name().equals(user) && loginUser.password().equals(password) ){
                logger.info(" login success");
            }
            else {
                HttpStatus httpStatus= HttpStatus.UNAUTHORIZED;
                String error = httpStatus.getDescription();
                exchange.sendResponseHeaders(httpStatus.getCode(), error.length());
                exchange.getResponseBody().write(error.getBytes());
                exchange.close();
                throw new IOException(" can't login " );
            }

        } catch (IOException e) {
          logger.error("An error occurred",e);
        }
    }
}
