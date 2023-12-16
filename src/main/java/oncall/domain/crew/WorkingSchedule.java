package oncall.domain.crew;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import oncall.domain.day.WorkDay;
import oncall.domain.day.WorkDays;

public class WorkingSchedule {
    private final List<WorkCrew> workCrews = new ArrayList<>();

    public WorkingSchedule(WorkDays calender, Crews weekdayCrews, Crews weekendCrews) {
        for (WorkDay workDay : calender.getWorkDays()) {
            if (workDay.isWeekend() || workDay.isLegalHoliday()) {
                Crew crew = pickCrew(weekendCrews);
                workCrews.add(new WorkCrew(crew, workDay));
                continue;
            }
            Crew crew = pickCrew(weekdayCrews);
            workCrews.add(new WorkCrew(crew, workDay));
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
