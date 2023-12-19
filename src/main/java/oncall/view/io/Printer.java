package oncall.view.io;

public class Printer {
    public static void printMessage(String message) {
        System.out.print(message);
    }

    public static void printMessageLine(String message) {
        System.out.println(message);
    }

    public static void printMessageUsingFormat(String format, Object... args) {
        System.out.printf(format, args);
    }
}
