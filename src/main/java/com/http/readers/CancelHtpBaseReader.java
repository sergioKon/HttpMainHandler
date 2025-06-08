package com.http.readers;

import com.sun.net.httpserver.HttpExchange;

public class CancelHtpBaseReader extends HttpBaseReader {
    public CancelHtpBaseReader(HttpExchange httpExchange) {
        super(httpExchange);
    }

    @Override
    public void process() {

    }
}
