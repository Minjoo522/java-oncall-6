package oncall.domain;


import java.time.LocalDate;
import java.util.Arrays;

public enum LegalHolidays {
    NEW_YEARS_DAY(1, 1),
    INDEPENDENCE_MOVEMENT_DAY(3, 1),
    CHILDREN_DAY(5, 5),
    MEMORIAL_DAY(6, 6),
    NATIONAL_LIBERATION_DAY(8, 15),
    FOUNDATION_DAY(10, 3),
    HANGUL_DAY(10, 9),
    CHRISTMAS(12, 25);

    private final int month;
    private final int date;

    LegalHolidays(int month, int date) {
        this.month = month;
        this.date = date;
    }

    public static boolean isHoliday(int month, int date) {
        return Arrays.stream(LegalHolidays.values())
                .filter(holiday -> holiday.month == month)
                .anyMatch(holiday -> holiday.date == date);
    }
}
