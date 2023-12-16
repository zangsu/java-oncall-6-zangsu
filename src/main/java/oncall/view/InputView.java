package oncall.view;

import java.util.List;
import java.util.regex.Pattern;
import oncall.exception.OncallExceptionMaker;
import oncall.view.io.Printer;
import oncall.view.io.Reader;

public class InputView {
    public static final String MEMBER_DELIMITER = ",";
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d+,[월화수목금토일]$");

    public static String inputStartDate() {
        Printer.printMessage("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        String startDate = Reader.getString();
        validateDate(startDate);
        return startDate;
    }

    private static void validateDate(String startDate) {
        if (!DATE_PATTERN.matcher(startDate).matches()) {
            throw OncallExceptionMaker.INVALID_INPUT_FORMAT.makeException();
        }
    }

    public static List<String> inputWeekdayMembers() {
        return inputCrews("평일");
    }

    public static List<String> getWeekendMembers() {
        return inputCrews("휴일");
    }

    public static List<String> inputCrews(String weekday) {
        Printer.printMessage(weekday + " 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        return Reader.getStringsUsingDelimiter(MEMBER_DELIMITER);

    }
}
