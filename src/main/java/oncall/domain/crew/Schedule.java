package oncall.domain.crew;

import java.util.Collections;
import java.util.List;

public class Schedule {
    private final List<WorkCrew> schedule;

    public Schedule(List<WorkCrew> schedule) {
        this.schedule = schedule;
    }

    public List<WorkCrew> getSchedule() {
        return Collections.unmodifiableList(schedule);
    }
}
