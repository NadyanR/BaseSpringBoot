package person.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler
//    public ResponseEntity<PersonException> handleException(NoSuchPersonException exception){
//        PersonException data = new PersonException();
//        data.setInfo(exception.getMessage());
//        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler
    public ResponseEntity handleException(NoSuchPersonException exception){
        return new ResponseEntity (exception.getMessage(), HttpStatus.valueOf(exception.getExceptionType().getCode()));
    }

    @ExceptionHandler
    public ResponseEntity<PersonException> handleException(Exception exception){
        PersonException data = new PersonException();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
