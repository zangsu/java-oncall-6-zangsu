package oncall.domain.crew;

import java.util.List;
import oncall.exception.OncallExceptionMaker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CrewsTest {

    @Nested
    @DisplayName("생성 테스트")
    class 생성_테스트 {

        @Test
        @DisplayName("정상 생성 테스트")
        void 정상_생성_테스트() {
            Assertions.assertThatNoException()
                    .isThrownBy(() -> Crews.from(List.of("수아", "지은", "현주", "현아", "혜원")));
        }

        @Test
        @DisplayName("크루가 5명 미만인 경우 예외 발생 테스트")
        void 크루가_2명_미만인_경우_예외_발생_테스트() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> Crews.from(List.of("수아", "지은", "현주", "현아")))
                    .withMessage(OncallExceptionMaker.INVALID_CREW_SIZE.getMessage());
        }

        @Test
        @DisplayName("크루가 35명 초과인 경우 예외 발생 테스트")
        void 크루가_35명_초과인_경우_예외_발생_테스트() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> Crews.from(List.of(
                            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                            "31", "32", "33", "34", "35", "36"
                    )))
                    .withMessage(OncallExceptionMaker.INVALID_CREW_SIZE.getMessage());
        }

        @Test
        @DisplayName("크루가 중복인 경우 예외 발생 테스트")
        void 크루가_중복인_경우_예외_발생_테스트() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> Crews.from(List.of("수아", "지은", "현주", "현아", "혜원", "수아")))
                    .withMessage(OncallExceptionMaker.DUPLICATED_CREW.getMessage());
        }
    }

    @Nested
    @DisplayName("선택 테스트")
    class 선택_테스트 {

        @Test
        @DisplayName("크루 선택 테스트")
        void 크루_선택_테스트() {
            List<String> names = List.of("수아", "지은", "현주", "현아", "혜원");
            Crews crews = Crews.from(names);
            for (int i = 0; i < 10; i++) {
                Crew firstCrew = crews.getFirstCrew();
                Assertions.assertThat(firstCrew.getName())
                        .isEqualTo(names.get(i % names.size()));
            }
        }

        @Test
        @DisplayName("크루 선택 테스트")
        void 크루_선택_테스트2() {
            List<String> names = List.of("수아", "지은", "현주", "현아", "혜원");
            Crews crews = Crews.from(names);
            Crew prevCrew = crews.getFirstCrew();
            for (int i = 0; i < 10; i++) {
                Crew nextCrew = crews.getNextCrew(prevCrew);
                Assertions.assertThat(nextCrew.getName())
                        .isEqualTo(names.get((i + 1) % names.size()));
                prevCrew = nextCrew;
            }
        }
    }
}