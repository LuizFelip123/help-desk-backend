package com.valdir.helpdesk.resource.exceptions;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardError implements  Serializable {
    private static final long serialVersionUID = 1l;

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String  path;

    public StandardError(){
        super();
    }
    public StandardError(String error, String message, String path, Integer status, Long timestamp) {
        this.error = error;
        this.message = message;
        this.path = path;
        this.status = status;
        this.timestamp = timestamp;
    }

    
}
