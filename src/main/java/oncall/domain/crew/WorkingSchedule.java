package oncall.domain.crew;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import oncall.domain.day.WorkDay;
import oncall.domain.day.WorkDays;

public class WorkingSchedule {
    private final List<WorkCrew> workCrews = new ArrayList<>();

    public WorkingSchedule(WorkDays workDays, AllCrews crews) {
        for (WorkDay workDay : workDays.getWorkDays()) {

            if (this.workCrews.isEmpty()) {
                workCrews.add(crews.getFirstCrew(workDay));
                continue;
            }
            Crew prevCrew = workCrews.get(workCrews.size() - 1).getCrew();
            workCrews.add(crews.getNextCrew(workDay, prevCrew));
        }
    }
    
    public List<WorkCrew> getWorkCrews() {
        return Collections.unmodifiableList(workCrews);
    }
}
