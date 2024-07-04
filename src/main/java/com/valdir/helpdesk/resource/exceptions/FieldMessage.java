package com.valdir.helpdesk.resource.exceptions;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldMessage implements  Serializable{
    private static final long serialVersionUID = 1l;
    private String filedName;
    private String message;

    public FieldMessage(String filedName, String message) {
        this.filedName = filedName;
        this.message = message;
    }

}
