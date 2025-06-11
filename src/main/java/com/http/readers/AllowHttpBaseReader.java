package com.http.readers;

import com.sun.net.httpserver.HttpExchange;

public class AllowHttpBaseReader extends HttpBaseReader {
    public AllowHttpBaseReader(HttpExchange httpExchange) {
        super(httpExchange);
        logger.warn(" init {}", AllowHttpBaseReader.class.getCanonicalName());
    }

    @Override
    public void process() {

    }

}
