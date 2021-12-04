package hrms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class MernisInvalidUserException extends RuntimeException {

    public MernisInvalidUserException(String message){
        super(message);
    }

}
