package com.http.response;


public class UserResponse {
   private  HttpStatus httpStatus;
    public UserResponse(){
        this(HttpStatus.OK);
    }

    public UserResponse(HttpStatus httpStatus){
        this.httpStatus = httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getHttpStatus() {
        return  httpStatus.getDescription();
    }
}
