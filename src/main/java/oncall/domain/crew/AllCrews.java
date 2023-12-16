package oncall.domain.crew;

import oncall.domain.day.WorkDay;

public class AllCrews {
    private final Crews weekdayCrews;
    private final Crews weekendCrews;

    public AllCrews(Crews weekdayCrews, Crews weekendCrews) {
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
}
