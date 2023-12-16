package oncall.view;

import java.util.List;
import oncall.domain.crew.WorkCrew;
import oncall.view.io.Printer;

public class OutputView {
    public static final String EXCEPTION_PREFIX = "[ERROR] ";

    public static void printException(Exception e) {
        Printer.printMessage(EXCEPTION_PREFIX + e.getMessage());
    }

    public static void newLine() {
        Printer.printMessage("");
    }

    public static void printSchedule(List<WorkCrew> workCrews) {
        
    }
}
