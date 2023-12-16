package oncall.domain.crew;

import java.util.Objects;
import oncall.exception.OncallExceptionMaker;

public class Crew {
    public static final int MAX_NAME_LEN = 5;

    private final String name;

    public Crew(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw OncallExceptionMaker.BLANK_NAME.makeException();
        }

        if (name.length() > MAX_NAME_LEN) {
            throw OncallExceptionMaker.TOO_LONG_NAME.makeException();
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Crew crew = (Crew) o;
        return Objects.equals(name, crew.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
