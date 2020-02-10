package br.com.uds.acaipersonalizados.api.exception;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

final class ResponseError {

    private final int code;

    private String message;

    private final String path;

    private List<ResponseFieldError> errors = Lists.newArrayList();

    ResponseError(HttpServletRequest request, HttpStatus httpStatus) {
        this.path = getRequestURI(request);
        this.code = getStatusCode(httpStatus);
    }

    ResponseError(HttpServletRequest request, RuntimeException ex, HttpStatus httpStatus) {
        this(request, httpStatus);
        this.message = ex.getMessage();
    }

    ResponseError(HttpServletRequest request, MethodArgumentNotValidException ex, HttpStatus httpStatus) {
        this(request, httpStatus);
        final var result = ex.getBindingResult();
        final List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();

        this.message = processFieldErrors(fieldErrors).orElse(ex.getMessage());
    }

    private Optional<String> processFieldErrors(List<FieldError> fieldErrors) {
        return fieldErrors.stream().map(fieldError ->
                "O campo ".concat(fieldError.getField())
                        .concat(" ").concat(fieldError.getDefaultMessage())).findFirst();
    }

    private String getRequestURI(HttpServletRequest request) {
        return request.getRequestURI();
    }

    private int getStatusCode(HttpStatus httpStatus) {
        return httpStatus.value();
    }

    public List<ResponseFieldError> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public int getCode() {
        return code;
    }
}