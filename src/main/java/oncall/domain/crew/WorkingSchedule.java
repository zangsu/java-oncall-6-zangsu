package oncall.domain.crew;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import oncall.domain.day.WorkDay;
import oncall.domain.day.WorkDayCalender;

public class WorkingSchedule {
    private final List<WorkCrew> workCrews = new ArrayList<>();

    public WorkingSchedule(WorkDayCalender calender, Crews weekdayCrews, Crews weekendCrews) {

        List<WorkDay> workDays = calender.getWorkDays();
        for (WorkDay workDay : workDays) {
            if (workDay.isWeekend() || workDay.isLegalHoliday()) {
                Crew crew = pickCrew(weekendCrews);
                boolean isLegalHoliday = !workDay.isWeekend()
                        && workDay.isLegalHoliday();
                workCrews.add(new WorkCrew(crew, workDay, isLegalHoliday));
                continue;
            }
            Crew crew = pickCrew(weekdayCrews);
            workCrews.add(new WorkCrew(crew, workDay, false));
        }
    }

    private Crew pickCrew(Crews crews) {
        if (this.workCrews.isEmpty()) {
            return crews.getFirstCrew();
        }
        Crew prevCrew = workCrews.get(workCrews.size() - 1).getCrew();
        return crews.getNextCrew(prevCrew);
    }

    public List<WorkCrew> getWorkCrews() {
        return Collections.unmodifiableList(workCrews);
    }
}
