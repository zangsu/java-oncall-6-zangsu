package oncall.domain.day;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import oncall.exception.OncallExceptionMaker;

public enum KoreaDayOfWeek {

    MONDAY("월", DayOfWeek.MONDAY),
    TUESDAY("화", DayOfWeek.TUESDAY),
    WEDNESDAY("수", DayOfWeek.WEDNESDAY),
    THURSDAY("목", DayOfWeek.THURSDAY),
    FRIDAY("금", DayOfWeek.FRIDAY),
    SATURDAY("토", DayOfWeek.SATURDAY),
    SUNDAY("일", DayOfWeek.SUNDAY);
    public static final Map<String, KoreaDayOfWeek> CACHED_DAY_OF_WEEK = Arrays.stream(values())
            .collect(Collectors.toMap(KoreaDayOfWeek::getName, Function.identity()));

    private final String name;

    private final DayOfWeek dayOfWeek;

    KoreaDayOfWeek(String name, DayOfWeek dayOfWeek) {
        this.name = name;
        this.dayOfWeek = dayOfWeek;
    }

    public static KoreaDayOfWeek of(DayOfWeek dayOfWeek) {
        return Arrays.stream(values())
                .filter(koreaDayOfWeek -> koreaDayOfWeek.dayOfWeek == dayOfWeek)
                .findFirst()
                .orElseThrow(OncallExceptionMaker.INVALID_DAY_OF_WEEK::makeException);
    }

    public static KoreaDayOfWeek nameOf(String name) {
        return Optional.ofNullable(CACHED_DAY_OF_WEEK.get(name))
                .orElseThrow(OncallExceptionMaker.INVALID_DAY_OF_WEEK::makeException);
    }

    public String getName() {
        return name;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
