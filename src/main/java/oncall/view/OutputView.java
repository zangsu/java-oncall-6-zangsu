package oncall.view;

import oncall.domain.crew.Schedule;
import oncall.domain.crew.WorkCrew;
import oncall.domain.day.WorkDay;
import oncall.view.io.Printer;

public class OutputView {
    public static final String EXCEPTION_PREFIX = "[ERROR] ";
    public static final String FORMAT_DAY_MESSAGE = "%d월 %d일 %s ";
    public static final String HOLYDAY_MESSAGE = "(휴일)";

    public static void printException(Exception e) {
        Printer.printMessageLine(EXCEPTION_PREFIX + e.getMessage());
    }

    public static void newLine() {
        Printer.printMessageLine("");
    }

    public static void printSchedule(Schedule schedule) {
        for (WorkCrew workCrew : schedule.getSchedule()) {
            printDay(workCrew.getWorkDay());
            Printer.printMessageLine(workCrew.getCrewName());
        }
    }

    private static void printDay(WorkDay workDay) {
        Printer.printMessageUsingFormat(FORMAT_DAY_MESSAGE, workDay.getMonth(), workDay.getDay(),
                workDay.getDayOfWeekName());
        if (workDay.isWeekday() && workDay.isLegalHoliday()) {
            Printer.printMessage(HOLYDAY_MESSAGE);
        }
    }
}
