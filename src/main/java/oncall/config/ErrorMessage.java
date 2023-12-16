package oncall.config;

public enum ErrorMessage {
    NOT_EMPTY("공백을 입력할 수 없습니다"),
    INVALID_INPUT("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    INVALID_MONTH_INPUT("월은 1 ~ 12의 숫자를 입력해야 합니다. 다시 입력해 주세요."),
    INVALID_DAY_OF_WEEK_INPUT("올바르지 않은 요일입니다. 다시 입력해 주세요.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;
    ErrorMessage(String message) {
        this.message = ERROR_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
