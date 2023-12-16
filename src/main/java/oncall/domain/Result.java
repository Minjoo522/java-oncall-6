package oncall.domain;

public class Result {
    private final Schedule schedule;
    private final Staffs staffs;

    public Result(Schedule schedule, Staffs staffs) {
        this.schedule = schedule;
        this.staffs = staffs;
    }
}
