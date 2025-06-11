package com.http.readers;

import com.http.response.HttpStatus;
import com.sun.net.httpserver.HttpExchange;


import java.io.IOException;
import java.io.OutputStream;


public class DefaultHtpBaseReader extends HttpBaseReader {
    public DefaultHtpBaseReader(HttpExchange httpExchange) {
        super(httpExchange);
    }

    @Override
    public void process() {
        HttpStatus httpStatus= HttpStatus.NOT_IMPLEMENTED;
        httpStatus.setDescription(" still not implement  ");
        userResponse.setHttpStatus(httpStatus);
    }

    public  void sendResponse()  {
        String message = userResponse.getHttpStatus();
        try (OutputStream os = httpExchange.getResponseBody()) {
            httpExchange.sendResponseHeaders(HttpStatus.NOT_FOUND.getCode(), message.getBytes().length);
            os.write(message.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void sendResponse(String message)  {

        try (OutputStream os = httpExchange.getResponseBody()) {
            httpExchange.sendResponseHeaders(500, message.getBytes().length);
            os.write(message.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
