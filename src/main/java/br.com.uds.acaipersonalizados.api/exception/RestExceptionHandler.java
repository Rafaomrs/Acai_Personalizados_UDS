package br.com.uds.acaipersonalizados.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler {
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    ResponseError handle(HttpServletRequest request, IllegalArgumentException ex) {
        return new ResponseError(request, ex, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ResponseError handle(HttpServletRequest request, MethodArgumentNotValidException ex) {
        return new ResponseError(request, ex, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseBody
    ResponseError handle(HttpServletRequest request, OrderNotFoundException ex) {
        return new ResponseError(request, ex, HttpStatus.NOT_FOUND);
    }
}
