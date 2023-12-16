package oncall.domain;

import java.util.List;

public enum EndDateOfMonth {
    TO_THIRTY(List.of(1, 3, 5, 7, 8, 10, 12), 30),
    TO_THIRTY_ONE(List.of(4, 6, 9, 11), 31),
    TO_TWENTY_EIGHT(List.of(2), 28);

    private final List<Integer> months;
    private final int endDate;

    EndDateOfMonth(List<Integer> months, int endDate) {
        this.months = months;
        this.endDate = endDate;
    }

    public List<Integer> getMonths() {
        return this.months;
    }

    public int getEndDate() {
        return this.endDate;
    }
}
