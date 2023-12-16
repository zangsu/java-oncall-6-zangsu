package oncall.exception.handler;

import java.util.function.Supplier;
import oncall.view.OutputView;

/**
 * XXXException 을 전달받아 예상한 예외에 대해서만 재시도, 그 외의 예외는 throw
 */
public class RetryHandler {

    public static <T> T getOrRetry(Supplier<T> supplier) {

        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printException(e);
            } finally {
                OutputView.newLine();
            }
        }
    }
}
