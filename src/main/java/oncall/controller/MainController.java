package oncall.controller;

import oncall.domain.Schedule;
import oncall.view.InputView;
import oncall.view.OutputView;

public class MainController {
    public void run() {
        Schedule schedule = getSchedule();
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
}
