package oncall;

import oncall.domain.day.WorkDayCalender;
import oncall.exception.handler.RetryHandler;
import oncall.view.InputView;

public class OncallController {
    public void run() {
        WorkDayCalender callendar = RetryHandler.getOrRetry(() -> getCalleandar());
    }

    private WorkDayCalender getCalleandar() {
        String startDate = InputView.inputStartDate();
        return WorkDayCalender.from(startDate);
    }
}
