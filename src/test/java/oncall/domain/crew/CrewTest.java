package oncall.domain.crew;

import static org.junit.jupiter.api.Assertions.assertEquals;

import oncall.exception.OncallExceptionMaker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CrewTest {
    @Nested
    @DisplayName("생성 테스트")
    class 생성_테스트 {
        @Test
        @DisplayName("정상 생성 테스트")
        void 정상_생성_테스트() {
            Crew crew = new Crew("수아");
            assertEquals("수아", crew.getName());
        }

        @Test
        @DisplayName("이름이 null인 경우 예외 발생 테스트")
        void 이름이_null인_경우_예외_발생_테스트() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Crew(null))
                    .withMessage(OncallExceptionMaker.BLANK_NAME.getMessage());
        }

        @Test
        @DisplayName("이름이 공백인 경우 예외 발생 테스트")
        void 이름이_공백인_경우_예외_발생_테스트() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Crew(""))
                    .withMessage(OncallExceptionMaker.BLANK_NAME.getMessage());
        }

        @Test
        @DisplayName("이름이 5자를 초과하는 경우 예외 발생 테스트")
        void 이름이_5자를_초과하는_경우_예외_발생_테스트() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Crew("123456"))
                    .withMessage(OncallExceptionMaker.TOO_LONG_NAME.getMessage());
        }
    }

    @Nested
    @DisplayName("비교 테스트")
    class 비교_테스트 {
        @Test
        @DisplayName("같은 이름을 가진 크루 비교 테스트")
        void 같은_이름을_가진_크루_비교_테스트() {
            Crew crew1 = new Crew("수아");
            Crew crew2 = new Crew("수아");
            Assertions.assertThat(crew1)
                    .isEqualTo(crew2);
        }
    }

}