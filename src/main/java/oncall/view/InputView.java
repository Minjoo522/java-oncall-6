package oncall.view;

import static oncall.config.ErrorMessage.INVALID_INPUT;
import static oncall.config.ErrorMessage.NOT_EMPTY;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class InputView {
    private InputView() {
        // 인스턴스 생성 방지용
    }

    public static List<String> readMonthAndDayOfWeek() throws IllegalArgumentException {
        String input = readInput(Message.REQUEST_MONTH_AND_DAY_OF_WEEK);
        validateInput(input);

        List<String> result = splitByComma(input);
        if (result.size() != 2) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
        return splitByComma(input);
    }

    public static List<String> readWeekdaysStaffNames() {
        String input = readInput(Message.REQUEST_WEEKDAYS_STAFF_NAMES);
        validateInput(input);
        return splitByComma(input);
    }

    private static void validateInput(String input) throws IllegalArgumentException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(NOT_EMPTY.getMessage());
        }
    }

    private static List<String> splitByComma(String input) {
        return Arrays.stream(input.split(","))
                .collect(Collectors.toList());
    }

    private static String readInput(Message requestMessage) {
        System.out.print(requestMessage.getMessage());
        return Console.readLine();
    }

    private enum Message {
        REQUEST_MONTH_AND_DAY_OF_WEEK("비상 근무를 배정할 월과 시작 요일을 입력하세요> "),
        REQUEST_WEEKDAYS_STAFF_NAMES("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");

        private final String message;

        Message(String message) {
            this.message = message;
        }

        private String getMessage() {
            return this.message;
        }
    }
}