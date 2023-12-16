package oncall.domain.crew;

import oncall.domain.day.WorkDay;

public class WorkCrew {
    private final Crew crew;
    private final WorkDay workDay;

    public WorkCrew(Crew crew, WorkDay workDay) {
        this.crew = crew;
        this.workDay = workDay;
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
