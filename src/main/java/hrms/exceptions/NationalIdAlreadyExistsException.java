package hrms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NationalIdAlreadyExistsException extends RuntimeException {

    public NationalIdAlreadyExistsException(String message) {
        super(message);
    }

}
