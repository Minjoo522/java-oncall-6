package oncall.domain;

public enum DayOfWeek {
    MONDAY("월", "평일"),
    TUESDAY("화", "평일"),
    WEDNESDAY("수", "평일"),
    THURSDAY("목", "평일"),
    FRIDAY("금", "평일"),
    SATURDAY("토", "휴일"),
    SUNDAY("일", "휴일");

    private final String message;
    private final String category;

    DayOfWeek(String message, String category) {
        this.message = message;
        this.category = category;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCategory() {
        return this.category;
    }
}
