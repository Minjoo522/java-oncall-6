package oncall.config;

import static oncall.config.Config.MAXIMUM_NAME_LENGTH;
import static oncall.config.Config.MAXIMUM_STAFFS_SIZE;
import static oncall.config.Config.MINIMUM_STAFFS_SIZE;

public enum ErrorMessage {
    NOT_EMPTY("공백을 입력할 수 없습니다"),
    INVALID_INPUT("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    INVALID_MONTH_INPUT("월은 1 ~ 12의 숫자를 입력해야 합니다. 다시 입력해 주세요."),
    INVALID_DAY_OF_WEEK_INPUT("올바르지 않은 요일입니다. 다시 입력해 주세요."),
    NOT_EMPTY_NAME("사원의 이름을 공백으로 입력할 수 없습니다."),
    NAME_DUPLICATED("중복된 사원을 입력할 수 없습니다"),
    NAME_LENGTH_OUT_OF_BOUND(
            String.format(
                    "사원의 이름은 %d자를 초과할 수 없습니다.",
                    MAXIMUM_NAME_LENGTH.getValue()
            )
    ),
    STAFFS_SIZE_OUT_OF_BOUND(
            String.format(
                    "사원 수는 %d자 이상 %d자 이하여야 합니다.",
                    MINIMUM_STAFFS_SIZE.getValue(),
                    MAXIMUM_STAFFS_SIZE.getValue()
            )
    );

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;
    ErrorMessage(String message) {
        this.message = ERROR_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
