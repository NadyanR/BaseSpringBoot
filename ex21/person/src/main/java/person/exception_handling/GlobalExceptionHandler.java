package person.exception_handling;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import person.services.EmailSenderService;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    private final EmailSenderService emailSenderService;

    public GlobalExceptionHandler(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @ExceptionHandler
    public ResponseEntity handleException(NoSuchPersonException exception){
        try {
            emailSenderService.sendSimpleEmail("navryabova@gmail.com", "Request Status_ERROR",
                    String.format("Ошибка:\n %s, %s", exception.getMessage(),exception.getExceptionType().getCode()));
        } catch (MailException mailException) {
            log.error("Error while sending out email..{}", mailException.getStackTrace());
            mailException.printStackTrace();
        }
            return new ResponseEntity (exception.getMessage(), HttpStatus.valueOf(exception.getExceptionType().getCode()));
    }

    @ExceptionHandler
    public ResponseEntity<PersonException> handleException(Exception exception){
        PersonException data = new PersonException();
        data.setInfo(exception.getMessage());
        try {
            emailSenderService.sendSimpleEmail("navryabova@gmail.com", "Request Status_ERROR",
                    String.valueOf(data));
        } catch (MailException mailException) {
            log.error("Error while sending out email..{}", mailException.getStackTrace());
            mailException.printStackTrace();
        }
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        try {
            emailSenderService.sendSimpleEmail("navryabova@gmail.com", "Request Status_ERROR",
                    String.format("Ошибка валидации:\n %s", errors));
        } catch (MailException mailException) {
            log.error("Error while sending out email..{}", mailException.getStackTrace());
            mailException.printStackTrace();
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}