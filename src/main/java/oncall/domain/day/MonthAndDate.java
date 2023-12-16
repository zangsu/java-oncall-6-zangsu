package oncall.domain.day;

import java.util.Objects;
import oncall.exception.OncallExceptionMaker;

public class MonthAndDate {
    private final Month month;
    private final int day;

    public MonthAndDate(Month month, int day) {
        validateDay(month, day);
        this.month = month;
        this.day = day;
    }

    private void validateDay(Month month, int day) {
        if (!month.hasDay(day)) {
            throw OncallExceptionMaker.INVALID_DAY.makeException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MonthAndDate workDay = (MonthAndDate) o;
        return day == workDay.day && month == workDay.month;
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, day);
    }

    public int getMonth() {
        return month.getMonth();
    }

    public int getDay() {
        return day;
    }
}
