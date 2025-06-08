package com.http.readers;

import com.http.response.HttpStatus;
import com.sun.net.httpserver.HttpExchange;

public class ApproveHttpBaseReader extends HttpBaseReader {
    public ApproveHttpBaseReader(HttpExchange httpExchange) {
        super(httpExchange);
    }

    @Override
    public void process() {
       HttpStatus httpStatus= HttpStatus.OK;
       httpStatus.setDescription(" approve ");
        userResponse.setHttpStatus(HttpStatus.OK);

    }
}
