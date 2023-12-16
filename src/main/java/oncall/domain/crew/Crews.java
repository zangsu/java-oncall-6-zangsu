package oncall.domain.crew;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import oncall.exception.OncallExceptionMaker;

public class Crews {
    public static final int MAX_CREW_SIZE = 35;
    public static final int MIN_CREW_SIZE = 5;
    private final Deque<CrewName> crews;

    private Crews(Collection<CrewName> crews) {
        validateCrews(crews);
        this.crews = new LinkedList<>(crews);
    }

    public static Crews from(Collection<String> crews) {
        List<CrewName> crewNames = crews.stream()
                .map(CrewName::new)
                .toList();
        return new Crews(crewNames);
    }

    private void validateCrews(Collection<CrewName> crews) {
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
