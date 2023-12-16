package oncall.domain;

import static oncall.config.Config.WEEKDAY_CATEGORY_NAME;
import static oncall.config.Config.WEEKEND_CATEGORY_NAME;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class Result {
    private static final String BASE = "%d월 %d일 %s %s";

    private final Schedule schedule;
    private final Staffs staffs;
    private int weekdayCount;
    private int weekendCount;

    public Result(Schedule schedule, Staffs staffs) {
        this.schedule = schedule;
        this.staffs = staffs;
    }

    public String getFinal() {
        List<DayOfWeek> dayOfWeeks = List.of(DayOfWeek.values());
        List<String> results = new ArrayList<>();
        int startIndex = dayOfWeeks.indexOf(schedule.getStartDayOfWeek());
        Staff yesterdayStaff = null;

        for (int i = 0; i < getEndDateOfMonth(); i++) {
            DayOfWeek dayOfWeek = dayOfWeeks.get((startIndex + i) % dayOfWeeks.size());
            // date는 1부터 시작하기 때문에 i + 1 함
            String category = findCategory(dayOfWeek, i + 1);
            Staff todayStaff = findTodayStaff(category, yesterdayStaff);

            yesterdayStaff = todayStaff;
            results.add(getOneResult(dayOfWeek, todayStaff, i + 1));
        }
        return String.join(System.lineSeparator(), results);
    }

    private int getEndDateOfMonth() {
        return schedule.findEndDate();
    }

    private String findCategory(DayOfWeek dayOfWeek, int date) {
        String category = dayOfWeek.getCategory();
        boolean isHoliday = LegalHolidays.isHoliday(schedule.getMonth(), date);
        if (isHoliday) {
            category = WEEKEND_CATEGORY_NAME;
        }
        return category;
    }

    private Staff findTodayStaff(String category, Staff yesterdayStaff) {
        Staff todayStaff = null;
        if (Objects.equals(category, WEEKDAY_CATEGORY_NAME)) {
            todayStaff = staffs.getStaff(category, weekdayCount);
            weekdayCount += 1;
        }
        if (Objects.equals(category, WEEKEND_CATEGORY_NAME)) {
            todayStaff = staffs.getStaff(category, weekendCount);
            weekendCount += 1;
        }
        if (yesterdayStaff == todayStaff) {
            todayStaff = searchNextStaff(category);
        }
        return todayStaff;
    }

    private Staff searchNextStaff(String category) {
        if (Objects.equals(category, WEEKDAY_CATEGORY_NAME)) {
            return staffs.getNextStaff(category, weekdayCount);
        }
        if (Objects.equals(category, WEEKEND_CATEGORY_NAME)) {
            return staffs.getNextStaff(category, weekendCount);
        }
        return null;
    }

    private String getOneResult(DayOfWeek dayOfWeek, Staff todayStaff, int date) {
        boolean isHoliday = LegalHolidays.isHoliday(schedule.getMonth(), date);
        if (isHoliday) {
            return String.format(
                    BASE, schedule.getMonth(), date, dayOfWeek.getMessage() + "(휴일)", todayStaff.getName()
            );
        }
        return String.format(BASE, schedule.getMonth(), date, dayOfWeek.getMessage(), todayStaff.getName());
    }
}
