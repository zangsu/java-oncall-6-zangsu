package oncall.domain.day;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import oncall.exception.OncallExceptionMaker;

public enum Month {
    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31);

    public static final Map<Integer, Month> CACHED_MONTHS = Arrays.stream(values())
            .collect(Collectors.toMap(Month::getMonthNumber, Function.identity()));
    private final int monthNumber;
    private final int lastDay;

    Month(int monthNumber, int lastDay) {
        this.monthNumber = monthNumber;
        this.lastDay = lastDay;
    }

    public Month of(int monthNumber) {
        return Optional.ofNullable(CACHED_MONTHS.get(monthNumber))
                .orElseThrow(OncallExceptionMaker.INVALID_MONTH::makeException);
    }

    private Integer getMonthNumber() {
        return monthNumber;
    }

    public boolean hasDay(int day) {
        return day >= 1 && day <= lastDay;
    }
}
