package oncall.domain.day;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class WorkDayCalender {
    public static final int WEEKDAY_SIZE = 7;
    public static final String DATE_DALIMITER = ",";
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
    private static final Map<Integer, DayOfWeek> dayOfWeeks = new HashMap<>();
    private final Month currentMonth;

    private WorkDayCalender(DayOfWeek startDayOfWeek, Month currentMonth) {
        this.currentMonth = currentMonth;
        for (int i = 1; i <= WEEKDAY_SIZE; i++) {
            dayOfWeeks.put(i % WEEKDAY_SIZE, startDayOfWeek.plus(i - 1));
        }
    }

    public static WorkDayCalender from(String startDate) {
        String[] split = startDate.split(DATE_DALIMITER);
        Month month = Month.of(Integer.parseInt(split[0]));
        KoreaDayOfWeek dayOfWeek = KoreaDayOfWeek.nameOf(split[1]);
        return new WorkDayCalender(dayOfWeek.getDayOfWeek(), month);
    }

    private DayOfWeek getDayOfWeek(int day) {
        return dayOfWeeks.get(day % WEEKDAY_SIZE);
    }


    public List<WorkDay> getWorkDays() {
        return IntStream.range(1, currentMonth.getLastDay() + 1)
                .mapToObj(day -> new WorkDay(new MonthAndDate(currentMonth, day), getDayOfWeek(day),
                        isLegalHoliday(currentMonth, day)))
                .toList();
    }

    private boolean isLegalHoliday(Month currentMonth, int day) {
        return legalHoliday.contains(new MonthAndDate(currentMonth, day));
    }
}
