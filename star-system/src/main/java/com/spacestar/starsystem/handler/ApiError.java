package com.spacestar.starsystem.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiError {

    private Integer status;
    private HttpStatus error;
    private String message;
    private List<ErrorField> fields;

    private ApiError() {
    }

    ApiError(HttpStatusCode statusCode) {
        this();
        this.status = statusCode.value();
        this.error = HttpStatus.resolve(statusCode.value());
    }

    ApiError(HttpStatusCode statusCode, String message, Throwable throwable) {
        this();
        this.status = statusCode.value();
        this.error = HttpStatus.resolve(statusCode.value());
        this.message = message;
    }

    void addValidationFieldError(List<FieldError> errorFieldList) {
        errorFieldList.forEach(error -> {
            var errorField = new ErrorField();
            errorField.setObject(error.getObjectName());
            errorField.setField(error.getField());
            errorField.setValue(error.getRejectedValue());
            errorField.setMessage(error.getDefaultMessage());
            addErrorField(errorField);
        });
    }

    void addValidationObjectError(List<ObjectError> objectErrorList) {
        objectErrorList.forEach(error -> {
            ErrorField errorField = new ErrorField();
            errorField.setObject(error.getObjectName());
            errorField.setField("");
            errorField.setValue("");
            errorField.setMessage(error.getDefaultMessage());
            addErrorField(errorField);
        });
    }

    private void addErrorField(ErrorField errorField) {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        if (fields.stream().noneMatch(item -> item.getObject().equals(errorField.getObject()) && item.getMessage().equals(errorField.getMessage()))) {
            fields.add(errorField);
        }
    }
}