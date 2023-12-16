package oncall;

import java.util.List;
import oncall.domain.crew.Crews;
import oncall.domain.crew.ScheduleMaker;
import oncall.domain.crew.WorkCrew;
import oncall.domain.day.WorkDays;
import oncall.exception.handler.RetryHandler;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OncallController {
    public void run() {
        WorkDays workDays = RetryHandler.getOrRetry(() -> getCalendar());
        ScheduleMaker scheduleMaker = RetryHandler.getOrRetry(() -> getCrews());

        //WorkingSchedule workingSchedule = new WorkingSchedule(workDays, scheduleMaker);
        List<WorkCrew> workCrews = scheduleMaker.makeSchedule(workDays);
        OutputView.printSchedule(workCrews);
    }

    private WorkDays getCalendar() {
        String startDate = InputView.inputStartDate();
        return WorkDays.from(startDate);
    }

    private ScheduleMaker getCrews() {
        Crews weekdayCrews = getWeekDayCrews();
        Crews weekendCrews = getWeekendCrews();
        return new ScheduleMaker(weekdayCrews, weekendCrews);
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
