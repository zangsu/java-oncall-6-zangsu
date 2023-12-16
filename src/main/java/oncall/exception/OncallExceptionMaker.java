package oncall.exception;

public enum OncallExceptionMaker {
    INVALID_NUMBER_FORMAT("숫자를 입력해 주세요"),
    INVALID_INPUT_FORMAT("입력 형식이 잘못되었습니다."),

    BLANK_INPUT("입력값이 비어 있습니다."),
    INVALID_MONTH("유효하지 않은 월입니다."),
    INVALID_DAY("유효하지 않은 일입니다."),
    INVALID_CREW_SIZE("크루의 크기는 5명 이상 35명 이하여야 합니다."),
    DUPLICATED_CREW("크루의 이름은 중복될 수 없습니다."),
    INVALID_DAY_OF_WEEK("유효하지 않은 요일입니다."),
    BLANK_NAME("이름은 공백이 될 수 없습니다."),
    TOO_LONG_NAME("이름은 5자 이하여야 합니다."),
    EMPTY_CREW("크루는 비어 있을 수 없습니다.");

    private final String message;
    private final IllegalArgumentException exception;

    OncallExceptionMaker(String message) {
        this.message = message;
        this.exception = new IllegalArgumentException(this.message);
    }

    public IllegalArgumentException makeException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }
}
