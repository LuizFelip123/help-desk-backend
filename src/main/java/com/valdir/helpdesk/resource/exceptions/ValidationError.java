package com.valdir.helpdesk.resource.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends  StandardError {
    private static final long serialVersionUID = 1l;

    private final List<FieldMessage> errors = new ArrayList<>();

    public ValidationError() {
    }

    public ValidationError(String error, String message, String path, Integer status, Long timestamp) {
        super(error, message, path, status, timestamp);
    }

    public List<FieldMessage> getErrors() {
        return this.errors;
    }
    public  void addError(String fieldName, String message){
        this.errors.add(new FieldMessage(fieldName, message));
    }
    
}
