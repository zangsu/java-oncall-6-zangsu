package oncall.view;

import java.util.List;
import java.util.regex.Pattern;
import oncall.exception.OncallExceptionMaker;
import oncall.view.io.Printer;
import oncall.view.io.Reader;

public class InputView {
    public static final String MEMBER_DELIMITER = ",";
    public static final String INPUT_CREW_MESSAGE = " 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    public static final String HOLYDAY = "휴일";
    public static final String WEEKDAY = "평일";
    public static final String INPUT_DAY_MESSAGE = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
    //월,요일 정규식
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d+,[월화수목금토일]$");

    public static String inputStartDate() {
        Printer.printMessage(INPUT_DAY_MESSAGE);
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
        return inputCrews(WEEKDAY);
    }

    public static List<String> getWeekendMembers() {
        return inputCrews(HOLYDAY);
    }

    public static List<String> inputCrews(String weekday) {
        Printer.printMessage(weekday + INPUT_CREW_MESSAGE);
        return Reader.getStringsUsingDelimiter(MEMBER_DELIMITER);

    }

    public static void close() {
        Reader.close();
    }
}
