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
            if (calender.isWeekend(workDay) || calender.isLegalHoliday(workDay)) {
                CrewName crewName = pickCrew(weekendCrews);
                boolean isLegalHoliday = !calender.isWeekend(workDay)
                        && calender.isLegalHoliday(workDay);
                workCrews.add(new WorkCrew(crewName, workDay, isLegalHoliday));
                continue;
            }
            CrewName crewName = pickCrew(weekdayCrews);
            workCrews.add(new WorkCrew(crewName, workDay, false));
        }
    }

    private CrewName pickCrew(Crews crews) {
        if (this.workCrews.isEmpty()) {
            return crews.getFirstCrew();
        }
        CrewName prevCrew = workCrews.get(workCrews.size() - 1).getCrewName();
        return crews.getNextCrew(prevCrew);
    }

    public List<WorkCrew> getWorkCrews() {
        return Collections.unmodifiableList(workCrews);
    }
}
