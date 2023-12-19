package oncall.domain.day;

import java.time.DayOfWeek;
import java.util.List;

public class WorkDay {
    private static final List<MonthAndDate> legalHoliday = List.of(
            new MonthAndDate(Month.JANUARY, 1),
            new MonthAndDate(Month.MARCH, 1),
            new MonthAndDate(Month.MAY, 5),
            new MonthAndDate(Month.JUNE, 6),
            new MonthAndDate(Month.AUGUST, 15),
            new MonthAndDate(Month.OCTOBER, 3),
            new MonthAndDate(Month.OCTOBER, 9),
            new MonthAndDate(Month.DECEMBER, 25)
    );

    private final MonthAndDate monthAndDate;
    private final KoreaDayOfWeek dayOfWeek;

    public WorkDay(MonthAndDate monthAndDate, DayOfWeek dayOfWeek) {
        this.monthAndDate = monthAndDate;
        this.dayOfWeek = KoreaDayOfWeek.of(dayOfWeek);
    }

    public boolean isWeekend() {
        return dayOfWeek == KoreaDayOfWeek.SATURDAY || dayOfWeek == KoreaDayOfWeek.SUNDAY;
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public boolean isLegalHoliday() {
        return legalHoliday.contains(monthAndDate);
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
}
