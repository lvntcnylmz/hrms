package hrms.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import hrms.core.utils.results.ErrorResult;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import hrms.core.utils.results.ErrorDataResult;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<String, String>();

        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ErrorDataResult<Object>(validationErrors, "Verification Error");
    }

    @ExceptionHandler
    public ErrorResult jobNotFoundException(JobNotFoundException exception) {
        return new ErrorResult(exception.getLocalizedMessage());
    }

    @ExceptionHandler
    public ErrorResult mernisInvalidUserException(MernisInvalidUserException exception){
        return new ErrorResult(exception.getLocalizedMessage());
    }

}
