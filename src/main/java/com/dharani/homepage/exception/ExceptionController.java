package com.dharani.homepage.exception;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
//rest
public class ExceptionController {

    private Log log = LogFactory.getLog(ExceptionController.class);

    @ExceptionHandler(value ={ InvalidPhNoException.class})
    public ResponseEntity<String> handleException(InvalidPhNoException ex)
    {
        log.error("Request:"+ ex.getMessage());
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value ={ NullPointerException.class})
    public ResponseEntity<Object> nullPointerException(NullPointerException ex)
    {
        log.error("Exception:"+ ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value ={ InvalidNameException.class})
    public ResponseEntity<Object> InvalidNameException(InvalidNameException ex)
    {
        log.error("Exception:"+ ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value ={ InvalidPasswordException.class})
    public ResponseEntity<Object> InvalidPasswordException(InvalidPasswordException ex)
    {
        log.error("Exception:"+ ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
