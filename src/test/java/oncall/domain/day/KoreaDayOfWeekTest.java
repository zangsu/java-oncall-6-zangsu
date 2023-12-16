package oncall.domain.day;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KoreaDayOfWeekTest {

    @Nested
    @DisplayName("요일 생성 테스트")
    class 요일_생성_테스트 {

        static Stream<Arguments> KoreanDayNameAndDayOfWeek() {
            return Stream.of(
                    Arguments.of(
                            "월", KoreaDayOfWeek.MONDAY
                    ),
                    Arguments.of(
                            "화", KoreaDayOfWeek.TUESDAY
                    ),
                    Arguments.of(
                            "수", KoreaDayOfWeek.WEDNESDAY
                    ),
                    Arguments.of(
                            "목", KoreaDayOfWeek.THURSDAY
                    ),
                    Arguments.of(
                            "금", KoreaDayOfWeek.FRIDAY
                    ),
                    Arguments.of(
                            "토", KoreaDayOfWeek.SATURDAY
                    ),
                    Arguments.of(
                            "일", KoreaDayOfWeek.SUNDAY
                    )
            );
        }

        @ParameterizedTest
        @MethodSource("KoreanDayNameAndDayOfWeek")
        @DisplayName("한글 이름으로 요일 조회 테스트")
        void 이름으로_요일_조회(String name, KoreaDayOfWeek expected) {
            KoreaDayOfWeek.nameOf(name)
                    .equals(expected);
        }
    }

}