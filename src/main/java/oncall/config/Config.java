package oncall.config;

public class Config {
    public static final String WEEKDAY_CATEGORY_NAME = "평일";
    public static final String WEEKEND_CATEGORY_NAME = "휴일";
    public static final int MAXIMUM_NAME_LENGTH = 5;
    public static final int MINIMUM_STAFFS_SIZE = 5;
    public static final int MAXIMUM_STAFFS_SIZE = 35;

    private Config() {
        // 인스턴스 생성 방지용
    }
}
