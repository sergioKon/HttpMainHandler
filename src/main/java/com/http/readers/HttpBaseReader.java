package com.http.readers;

import com.http.response.HttpStatus;
import com.http.response.UserResponse;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class HttpBaseReader {
    protected HttpExchange httpExchange;
    protected Headers headers;
    protected UserResponse userResponse;
    String body;
    protected final  Logger  logger= LogManager.getLogger(this.getClass());
    protected HttpBaseReader(HttpExchange httpExchange){
        logger.warn(" class  {} init ", super.getClass());
        this.httpExchange= httpExchange;
        userResponse =new UserResponse();
        InputStream inputStream = httpExchange.getRequestBody();
        try {
            body = new String(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        headers= httpExchange.getRequestHeaders();
        logger.info("{} reader started ", this.getClass().getCanonicalName() );
    }
    public abstract void process();
    public String  getBody(){
        return body;
    }
    public  void sendResponse()  {
        String message = userResponse.getHttpStatus();
        try (OutputStream os = httpExchange.getResponseBody()) {
            httpExchange.sendResponseHeaders(HttpStatus.OK.getCode(), message.getBytes().length);
            os.write(message.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
