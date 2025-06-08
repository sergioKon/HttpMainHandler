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
        HttpBaseReader httpBaseReader;
        String message= "success";
        switch (relativePath) {
            case  "/cancel" :
                httpBaseReader = new CancelHtpBaseReader(httpExchange);
                break ;
            case  "/allow" :
                httpBaseReader = new AllowHttpBaseReader(httpExchange);
                break ;
            case  "/booking" :
                httpBaseReader = new BookingHttpBaseReader(httpExchange);
                break ;
            case  "/approve" :
                httpBaseReader = new ApproveHttpBaseReader(httpExchange);
                break ;
            default:
                LOGGER.warn(" url is not valid ");
                httpBaseReader = new DefaultHtpBaseReader(httpExchange);
                message = " default url is not implements ";
        }
            httpBaseReader.process();
            LOGGER.info(" the content of request =  {}" , httpBaseReader.getBody() );
            httpBaseReader.sendResponse();
    }
}
