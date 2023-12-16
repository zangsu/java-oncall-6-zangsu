package oncall.domain.crew;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import oncall.exception.OncallExceptionMaker;

public class Crews {
    public static final int MAX_CREW_SIZE = 35;
    public static final int MIN_CREW_SIZE = 5;
    private final Deque<Crew> crews;

    private Crews(Collection<Crew> crews) {
        validateCrews(crews);
        this.crews = new LinkedList<>(crews);
    }

    public static Crews from(Collection<String> crews) {
        List<Crew> crewNames = crews.stream()
                .map(Crew::new)
                .toList();

        return new Crews(crewNames);
    }

    private void validateCrews(Collection<Crew> crews) {
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

    public Crew getFirstCrew() {
        Crew first = crews.removeFirst();
        crews.addLast(first);
        return first;
    }

    public Crew getNextCrew(Crew prevCrew) {
        Crew next = crews.removeFirst();
        if (!next.equals(prevCrew)) {
            crews.addLast(next);
            return next;
        }
        Crew replacedCrew = crews.removeFirst();
        crews.addLast(replacedCrew);
        crews.addFirst(next);
        return replacedCrew;
    }
}
