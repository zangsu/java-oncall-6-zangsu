package oncall.domain.crew;

import oncall.domain.day.WorkDay;

public class WorkCrew {
    private final CrewName crewName;
    private final WorkDay workDay;
    private final boolean isLegalHoliday;

    public WorkCrew(CrewName crewName, WorkDay workDay, boolean isLegalHoliday) {
        this.crewName = crewName;
        this.workDay = workDay;
        this.isLegalHoliday = isLegalHoliday;
    }

    public CrewName getCrewName() {
        return crewName;
    }
}
