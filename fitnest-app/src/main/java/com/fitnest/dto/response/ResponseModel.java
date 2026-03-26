package com.fitnest.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModel<T> {
    private int statusCode;
    private String message;
    private T data;
    private Boolean status;
    private Long size;


    public ResponseModel(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public ResponseModel(int statusCode, String message, T data,Boolean status, Long size) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.size = size;
        this.status=status;
    }

    public ResponseModel(Boolean status, int statusCode, String message, T data) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

}
