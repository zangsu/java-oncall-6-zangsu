package oncall;

import java.util.List;
import oncall.domain.crew.Crews;
import oncall.domain.crew.WorkCrew;
import oncall.domain.crew.WorkingSchedule;
import oncall.domain.day.WorkDays;
import oncall.exception.handler.RetryHandler;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OncallController {
    public void run() {
        WorkDays callendar = RetryHandler.getOrRetry(() -> getCalendar());
        Crews weekdayCrews = RetryHandler.getOrRetry(() -> getWeekDayCrews());
        Crews weekendCrews = RetryHandler.getOrRetry(() -> getWeekendCrews());

        WorkingSchedule workingSchedule = new WorkingSchedule(callendar, weekdayCrews, weekendCrews);
        List<WorkCrew> workCrews = workingSchedule.getWorkCrews();
        OutputView.printSchedule(workCrews);
    }

    private WorkDays getCalendar() {
        String startDate = InputView.inputStartDate();
        return WorkDays.from(startDate);
    }

    private Crews getWeekDayCrews() {
        List<String> crews = InputView.inputWeekdayMembers();
        return Crews.from(crews);
    }

    private Crews getWeekendCrews() {
        List<String> crews = InputView.getWeekendMembers();
        return Crews.from(crews);
    }
}
