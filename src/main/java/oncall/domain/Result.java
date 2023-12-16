package oncall.domain;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private final Schedule schedule;
    private final Staffs staffs;

    public Result(Schedule schedule, Staffs staffs) {
        this.schedule = schedule;
        this.staffs = staffs;
    }

    public String getFinal() {
        List<DayOfWeek> dayOfWeeks = List.of(DayOfWeek.values());
        List<String> results = new ArrayList<>();
        int startIndex = dayOfWeeks.indexOf(schedule.getStartDayOfWeek());
        Staff yesterday = null;
        int weekdayCount = 0;
        int weekendsCount = 0;

        for (int i = 0; i < getEndDateOfMonth(); i++) {
            DayOfWeek dayOfWeek = dayOfWeeks.get((startIndex + i) % dayOfWeeks.size());
            String category = dayOfWeek.getCategory();
            boolean isHoliday = LegalHolidays.isHoliday(schedule.getMonth(), i + 1);
            String holidayMark = "";
            if (isHoliday) {
                category = "휴일";
                holidayMark = "(휴일)";
            }
            Staff today = null;
            if (category == "평일") {
                today = staffs.getStaff(category, weekdayCount);
                weekdayCount += 1;
            }
            if (category == "휴일") {
                today = staffs.getStaff(category, weekendsCount);
                weekendsCount += 1;
            }

            if (i != 1 && yesterday == today) {
                if (category == "평일") {
                    today = staffs.getNextStaff(category, weekdayCount);
                }
                if (category == "휴일") {
                    today = staffs.getNextStaff(category, weekendsCount);
                }
            }
            yesterday = today;
            if (isHoliday) {
                results.add(String.format("%d월 %d일 %s %s", schedule.getMonth(), i + 1, dayOfWeek.getMessage() + "(휴일)", today.getName()));
            }
            if (!isHoliday) {
                results.add(String.format("%d월 %d일 %s %s", schedule.getMonth(), i + 1, dayOfWeek.getMessage(), today.getName()));
            }
        }
        return String.join(System.lineSeparator(), results);
    }

    private int getEndDateOfMonth() {
        return schedule.findEndDate();
    }
}
