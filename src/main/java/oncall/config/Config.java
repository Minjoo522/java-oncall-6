package oncall.config;

public enum Config {
    MAXIMUM_NAME_LENGTH(5),
    MINIMUM_STAFFS_SIZE(5),
    MAXIMUM_STAFFS_SIZE(35);

    private final int value;

    Config(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
