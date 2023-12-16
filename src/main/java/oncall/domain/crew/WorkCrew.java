package oncall.domain.crew;

import oncall.domain.day.WorkDay;

public class WorkCrew {
    private final Crew crew;
    private final WorkDay workDay;
    private final boolean isLegalHoliday;

    public WorkCrew(Crew crew, WorkDay workDay, boolean isLegalHoliday) {
        this.crew = crew;
        this.workDay = workDay;
        this.isLegalHoliday = isLegalHoliday;
    }

    public String getCrewName() {
        return crew.getName();
    }

    public WorkDay getWorkDay() {
        return workDay;
    }

    public Crew getCrew() {
        return crew;
    }
}
