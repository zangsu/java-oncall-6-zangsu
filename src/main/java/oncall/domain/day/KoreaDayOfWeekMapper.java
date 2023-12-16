package oncall.domain.day;

import java.time.DayOfWeek;
import java.util.Map;
import java.util.Optional;
import oncall.exception.OncallExceptionMaker;

public class KoreaDayOfWeekMapper {

    public static final Map<String, DayOfWeek> CACHED_DAY_OF_WEEK = Map.of(
            "월", DayOfWeek.MONDAY,
            "화", DayOfWeek.TUESDAY,
            "수", DayOfWeek.WEDNESDAY,
            "목", DayOfWeek.THURSDAY,
            "금", DayOfWeek.FRIDAY,
            "토", DayOfWeek.SATURDAY,
            "일", DayOfWeek.SUNDAY
    );

    public static DayOfWeek getDayOfWeek(String name) {
        return Optional.ofNullable(CACHED_DAY_OF_WEEK.get(name))
                .orElseThrow(OncallExceptionMaker.INVALID_DAY_OF_WEEK::makeException);
    }
}
