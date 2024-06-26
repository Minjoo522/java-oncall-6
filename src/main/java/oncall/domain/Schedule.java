package oncall.domain;

import static oncall.config.ErrorMessage.INVALID_DAY_OF_WEEK_INPUT;
import static oncall.config.ErrorMessage.INVALID_MONTH_INPUT;
import static oncall.config.ErrorMessage.NOT_FOUND_END_DATE_OF_MONTH;

import java.util.Arrays;
import java.util.List;

public class Schedule {
    private final int month;
    private final DayOfWeek startDayOfWeek;

    public Schedule(List<String> input) {
        month = validateAndParseMonth(input.get(0));
        startDayOfWeek = validateAndParseDayOfWeek(input.get(1));
    }

    private int validateAndParseMonth(String month) {
        int parsedMonth = parseMonth(month);
        if (parsedMonth < 1 || parsedMonth > 12) {
            throw new IllegalArgumentException(INVALID_MONTH_INPUT.getMessage());
        }
        return parsedMonth;
    }

    private int parseMonth(String month) throws IllegalArgumentException {
        try {
            return Integer.parseInt(month);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_MONTH_INPUT.getMessage());
        }
    }

    private DayOfWeek validateAndParseDayOfWeek(String dayOfWeek) {
        return Arrays.stream(DayOfWeek.values())
                .filter(week -> week.getMessage().equals(dayOfWeek.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_DAY_OF_WEEK_INPUT.getMessage()));
    }

    public int findEndDate() {
        return Arrays.stream(EndDateOfMonth.values())
                .filter(endDateOfMonth -> endDateOfMonth.getMonths().contains(month))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_END_DATE_OF_MONTH.getMessage()))
                .getEndDate();
    }

    public int getMonth() {
        return this.month;
    }

    public DayOfWeek getStartDayOfWeek() {
        return this.startDayOfWeek;
    }
}
