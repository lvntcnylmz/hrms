package hrms.exceptions;

import hrms.core.utils.results.ErrorDataResult;
import hrms.core.utils.results.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<String, String>();

        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(new ErrorDataResult<Object>(validationErrors, "Verification Error"), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<?> jobNotFoundException(JobNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResult(exception.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MernisInvalidUserException.class)
    public ResponseEntity<?> mernisInvalidUserException(MernisInvalidUserException exception) {
        return new ResponseEntity<>(new ErrorResult(exception.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResult(exception.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<?> incorrectPasswordException(IncorrectPasswordException exception) {
        return new ResponseEntity<>(new ErrorResult(exception.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> emailAlreadyExistsException(EmailAlreadyExistsException exception) {
        return new ResponseEntity<>(new ErrorResult(exception.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NationalIdAlreadyExistsException.class)
    public ResponseEntity<?> nationalIdAlreadyExists(NationalIdAlreadyExistsException exception) {
        return new ResponseEntity<>(new ErrorResult(exception.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

}
