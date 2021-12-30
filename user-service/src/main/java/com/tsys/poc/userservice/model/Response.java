package com.tsys.poc.userservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {
    @JsonInclude(Include.NON_NULL)
    private String message;
    @JsonInclude(Include.NON_NULL)
    private Object returnData;

}
