package oncall.domain.day;

import java.util.Objects;
import oncall.exception.OncallExceptionMaker;

public class WorkDay {
    private final Month month;
    private final int day;

    public WorkDay(Month month, int day) {
        validateDay(month, day);
        this.month = month;
        this.day = day;
    }

    private void validateDay(Month month, int day) {
        if (!month.hasDay(day)) {
            throw OncallExceptionMaker.INVALID_DAY.makeException();
        }
    }

    public int getDay() {
        return day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkDay workDay = (WorkDay) o;
        return day == workDay.day && month == workDay.month;
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, day);
    }
}
