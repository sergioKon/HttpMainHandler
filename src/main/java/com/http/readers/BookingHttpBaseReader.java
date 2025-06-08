package com.http.readers;

import com.sun.net.httpserver.HttpExchange;

public class BookingHttpBaseReader extends HttpBaseReader {
    public BookingHttpBaseReader(HttpExchange httpExchange) {
        super(httpExchange);
    }

    @Override
    public void process() {

    }

}
