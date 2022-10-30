package person.exception_handling;

public enum ErrorCodes {
    VALIDATION_PARSE_ERROR(400, "Пользователь не найден");

    private int code;
    private String message;

    ErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;}
    public String getMessage(){
        return message;}
}
