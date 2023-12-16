package oncall.view;

import oncall.domain.crew.Schedule;
import oncall.domain.crew.WorkCrew;
import oncall.domain.day.WorkDay;
import oncall.view.io.Printer;

public class OutputView {
    public static final String EXCEPTION_PREFIX = "[ERROR] ";

    public static void printException(Exception e) {
        Printer.printMessageLine(EXCEPTION_PREFIX + e.getMessage());
    }

    public static void newLine() {
        Printer.printMessage("");
    }

    public static void printSchedule(Schedule schedule) {
        for (WorkCrew workCrew : schedule.getSchedule()) {
            printDay(workCrew.getWorkDay());
            Printer.printMessageLine(workCrew.getCrewName());
        }
    }

    private static void printDay(WorkDay workDay) {
        Printer.printMessageUsingFormat("%d월 %d일 %s ", workDay.getMonth(), workDay.getDay(),
                workDay.getDayOfWeekName());
        if (workDay.isWeekday() && workDay.isLegalHoliday()) {
            Printer.printMessage("(휴일)");
        }
    }
}
