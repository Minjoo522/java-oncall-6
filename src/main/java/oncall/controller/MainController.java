package oncall.controller;

import java.util.List;
import oncall.domain.Schedule;
import oncall.domain.Staff;
import oncall.domain.Staffs;
import oncall.view.InputView;
import oncall.view.OutputView;

public class MainController {
    public void run() {
        Schedule schedule = getSchedule();
        Staffs staffs = getStaffs();
    }

    private Schedule getSchedule() {
        while (true) {
            try {
               return new Schedule(InputView.readMonthAndDayOfWeek());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

    private Staffs getStaffs() {
        while (true) {
            try {
                Staffs staffs = new Staffs(InputView.readWeekdaysStaffNames());
                return staffs;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }
}