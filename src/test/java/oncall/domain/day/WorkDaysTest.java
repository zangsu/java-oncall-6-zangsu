package oncall.domain.day;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class WorkDaysTest {

    @Nested
    @DisplayName("날짜 생성 테스트")
    class 날짜_생성_테스트 {

        @Test
        @DisplayName("5월 생성 테스트")
        void _5월_생성_테스트() {
            WorkDays calender = WorkDays.from("5,월");
            List<WorkDay> workDays = calender.getWorkDays();
            for (int i = 1; i <= 31; i++) {
                WorkDay workDay = workDays.get(i - 1);
                Assertions.assertThat(workDay.getMonth())
                        .isEqualTo(5);
                Assertions.assertThat(workDay.getDay())
                        .isEqualTo(i);
            }
        }
    }
}