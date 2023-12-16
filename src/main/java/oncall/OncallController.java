package oncall;

import java.util.List;
import oncall.domain.crew.AllCrews;
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
        AllCrews crews = RetryHandler.getOrRetry(() -> getCrews());

        WorkingSchedule workingSchedule = new WorkingSchedule(callendar, crews);
        List<WorkCrew> workCrews = workingSchedule.getWorkCrews();
        OutputView.printSchedule(workCrews);
    }

    private WorkDays getCalendar() {
        String startDate = InputView.inputStartDate();
        return WorkDays.from(startDate);
    }

    private AllCrews getCrews() {
        Crews weekdayCrews = getWeekDayCrews();
        Crews weekendCrews = getWeekendCrews();
        return new AllCrews(weekdayCrews, weekendCrews);
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
