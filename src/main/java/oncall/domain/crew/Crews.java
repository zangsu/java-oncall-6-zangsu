package oncall.domain.crew;

import java.util.Deque;
import oncall.exception.OncallExceptionMaker;

public class Crews {
    public static final int MAX_CREW_SIZE = 35;
    public static final int MIN_CREW_SIZE = 5;
    private final Deque<CrewName> crews;

    public Crews(Deque<CrewName> crews) {
        validateCrews(crews);
        this.crews = crews;
    }

    private void validateCrews(Deque<CrewName> crews) {
        if (crews == null || crews.isEmpty()) {
            throw new IllegalArgumentException("크루는 null이거나 비어있을 수 없습니다.");
        }
        if (crews.size() < MIN_CREW_SIZE || crews.size() > MAX_CREW_SIZE) {
            throw OncallExceptionMaker.INVALID_CREW_SIZE.makeException();
        }
        int distinctCrewSize = (int) crews.stream()
                .distinct()
                .count();
        if (distinctCrewSize != crews.size()) {
            throw OncallExceptionMaker.DUPLICATED_CREW.makeException();
        }

    }

    public CrewName getFirstCrew() {
        CrewName first = crews.removeFirst();
        crews.addLast(first);
        return first;
    }

    public CrewName getNextCrew(CrewName prevCrew) {
        CrewName next = crews.removeFirst();
        if (!next.equals(prevCrew)) {
            crews.addLast(next);
            return getNextCrew(prevCrew);
        }
        CrewName replacedCrew = crews.removeFirst();
        crews.addLast(replacedCrew);
        crews.addFirst(next);
        return replacedCrew;
    }
}
