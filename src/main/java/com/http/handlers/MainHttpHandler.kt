package com.http.handlers;

import com.http.readers.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainHttpHandler implements HttpHandler {

    private static final Logger LOGGER = LogManager.getLogger(MainHttpHandler.class);    @Override
    public void handle(HttpExchange httpExchange) {
        String relativePath = httpExchange.getRequestURI().getPath();
        LOGGER.info(" main  handler started  {}", relativePath);


        HttpBaseReader httpBaseReader = switch (relativePath) {
            case "/cancel" -> new CancelHtpBaseReader(httpExchange);
            case "/allow" -> new AllowHttpBaseReader(httpExchange);
            case "/booking" -> new BookingHttpBaseReader(httpExchange);
            case "/approve" -> new ApproveHttpBaseReader(httpExchange);
            default -> {
               LOGGER.warn(" url is not valid ");
               yield new DefaultHtpBaseReader(httpExchange);
            }
        };
        httpBaseReader.process();
            LOGGER.info(" the content of request =  {}" , httpBaseReader.getBody() );
            httpBaseReader.sendResponse();
    }
}
