package guru.springframework.msscbrewery.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/*
ControllerAdvice - automatically gets added to all controllers
 */
@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandling(ConstraintViolationException cve) {
        List<String> errors = new ArrayList<>(cve.getConstraintViolations().size());
        cve.getConstraintViolations().forEach(cv -> {
            errors.add(cv.getPropertyPath() + " : " + cv.getMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List> handleBindException(BindException be) {
        return new ResponseEntity<>(be.getAllErrors(), HttpStatus.BAD_REQUEST);
    }
}
