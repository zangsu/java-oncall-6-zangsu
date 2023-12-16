package oncall.domain.crew;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.day.WorkDay;
import oncall.domain.day.WorkDays;

public class ScheduleMaker {
    private final Crews weekdayCrews;
    private final Crews weekendCrews;

    public ScheduleMaker(Crews weekdayCrews, Crews weekendCrews) {
        this.weekdayCrews = weekdayCrews;
        this.weekendCrews = weekendCrews;
    }

    public WorkCrew getFirstCrew(WorkDay workDay) {
        if (workDay.isWeekday()) {
            return new WorkCrew(weekdayCrews.getFirstCrew(), workDay);
        }
        return new WorkCrew(weekendCrews.getFirstCrew(), workDay);
    }

    public WorkCrew getNextCrew(WorkDay workDay, Crew prevCrew) {
        if (workDay.isWeekday()) {
            return new WorkCrew(weekdayCrews.getNextCrew(prevCrew), workDay);
        }
        return new WorkCrew(weekendCrews.getNextCrew(prevCrew), workDay);
    }

    public List<WorkCrew> makeSchedule(WorkDays workDays) {
        List<WorkCrew> workCrews = new ArrayList<>();
        for (WorkDay workDay : workDays.getWorkDays()) {

            if (workCrews.isEmpty()) {
                workCrews.add(getFirstCrew(workDay));
                continue;
            }

            Crew prevCrew = workCrews.get(workCrews.size() - 1).getCrew();
            workCrews.add(getNextCrew(workDay, prevCrew));
        }
        return workCrews;
    }
}
