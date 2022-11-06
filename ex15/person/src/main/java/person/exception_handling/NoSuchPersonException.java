package person.exception_handling;

import lombok.Getter;

@Getter
public class NoSuchPersonException extends RuntimeException{

//    public NoSuchPersonException(String message){
//        super(message);}

    private final ErrorCodes exceptionType;

    public NoSuchPersonException(ErrorCodes exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }

    public NoSuchPersonException(ErrorCodes exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }
}
