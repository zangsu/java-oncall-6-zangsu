package oncall.domain.crew;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CrewsTest {

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
                System.out.println(nextCrew.getName());
                Assertions.assertThat(nextCrew.getName())
                        .isEqualTo(names.get((i + 1) % names.size()));
                prevCrew = nextCrew;
            }
        }
    }
}