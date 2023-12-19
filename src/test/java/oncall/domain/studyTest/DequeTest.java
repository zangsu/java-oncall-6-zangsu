package oncall.domain.studyTest;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class DequeTest {
    public static final Deque<String> deque = new LinkedList<>(
            List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"));


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String first = deque.removeFirst();
            deque.addLast(first);
            System.out.println(deque);
        }
    }
}

