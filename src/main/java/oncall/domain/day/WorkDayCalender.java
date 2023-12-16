package oncall.domain.day;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkDayCalender {
    public static final int WEEKDAY_SIZE = 7;
    public static final String DATE_DALIMITER = ",";
    private static final List<WorkDay> legalHoliday = List.of(
            new WorkDay(Month.JANUARY, 1),
            new WorkDay(Month.MARCH, 1),
            new WorkDay(Month.MAY, 5),
            new WorkDay(Month.JUNE, 6),
            new WorkDay(Month.AUGUST, 15),
            new WorkDay(Month.OCTOBER, 3),
            new WorkDay(Month.OCTOBER, 9),
            new WorkDay(Month.DECEMBER, 25)
    );
    private static final Map<Integer, DayOfWeek> dayOfWeeks = new HashMap<>();
    private final Month currentMonth;

    private WorkDayCalender(DayOfWeek startDayOfWeek, Month currentMonth) {
        this.currentMonth = currentMonth;
        for (int i = 1; i < WEEKDAY_SIZE; i++) {
            dayOfWeeks.put(i % WEEKDAY_SIZE, startDayOfWeek.plus(i));
        }
    }

    public static WorkDayCalender from(String startDate) {
        String[] split = startDate.split(DATE_DALIMITER);
        Month month = Month.of(Integer.parseInt(split[0]));
        DayOfWeek dayOfWeek = KoreaDayOfWeekMapper.getDayOfWeek(split[1]);

        return new WorkDayCalender(dayOfWeek, month);
    }

    private DayOfWeek getDayOfWeek(int day) {
        return dayOfWeeks.get(day % WEEKDAY_SIZE);
    }

    public boolean isWeekend(WorkDay workDay) {
        DayOfWeek week = getDayOfWeek(workDay.getDay());
        return week == DayOfWeek.SATURDAY || week == DayOfWeek.SUNDAY;
    }

    public boolean isLegalHoliday(WorkDay workDay) {
        return legalHoliday.contains(workDay);
    }


}
