package oncall.domain.day;

import java.time.DayOfWeek;

public class WorkDay {
    private final MonthAndDate monthAndDate;
    private final KoreaDayOfWeek dayOfWeek;
    private final boolean isLegalHoliday;

    public WorkDay(MonthAndDate monthAndDate, DayOfWeek dayOfWeek, boolean isLegalHoliday) {
        this.monthAndDate = monthAndDate;
        this.dayOfWeek = KoreaDayOfWeek.of(dayOfWeek);
        this.isLegalHoliday = isLegalHoliday;
    }

    public boolean isWeekend() {
        return dayOfWeek == KoreaDayOfWeek.SATURDAY || dayOfWeek == KoreaDayOfWeek.SUNDAY;
    }

    public boolean isLegalHoliday() {
        return isLegalHoliday;
    }

    public int getMonth() {
        return monthAndDate.getMonth();
    }

    public int getDay() {
        return monthAndDate.getDay();
    }

    public String getDayOfWeekName() {
        return dayOfWeek.getName();
    }

    public boolean isWeekday() {
        return !isWeekend();
    }
}
