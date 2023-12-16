package oncall.domain.day;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class WorkDays {
    public static final int WEEKDAY_SIZE = 7;
    public static final String DATE_DALIMITER = ",";
    private static final Map<Integer, DayOfWeek> dayOfWeeks = new HashMap<>();
    private final List<WorkDay> workDays;

    private WorkDays(DayOfWeek startDayOfWeek, Month currentMonth) {
        for (int i = 1; i <= WEEKDAY_SIZE; i++) {
            dayOfWeeks.put(i % WEEKDAY_SIZE, startDayOfWeek.plus(i - 1));
        }
        this.workDays = IntStream.range(1, currentMonth.getLastDay() + 1)
                .mapToObj(day -> new WorkDay(new MonthAndDate(currentMonth, day), getDayOfWeek(day)))
                .toList();
    }

    public static WorkDays from(String startDate) {
        String[] split = startDate.split(DATE_DALIMITER);
        Month month = Month.of(Integer.parseInt(split[0]));
        KoreaDayOfWeek dayOfWeek = KoreaDayOfWeek.nameOf(split[1]);
        return new WorkDays(dayOfWeek.getDayOfWeek(), month);
    }

    private DayOfWeek getDayOfWeek(int day) {
        return dayOfWeeks.get(day % WEEKDAY_SIZE);
    }

    public List<WorkDay> getWorkDays() {
        return this.workDays;
    }
}
