import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void resizeTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
        for (int i = 1; i < 11; i++) {
            deque.addFirst(i);
        }
        Integer[] exp = {null, null, null, null, null, null, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        assertArrayEquals(exp, deque.items);

        for (int i = 0; i > -10; i--) {
            deque.addLast(i);
        }
        Integer[] exp1 = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, null, null, null, null, null,
                null, null, null, null, null, null, null, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        assertArrayEquals(exp1, deque.items);

        for (int i = 11; i < 21; i++) {
            deque.addFirst(i);
        }

        for (int i = 1; i < 26; i++) {
            deque.removeLast();
        }
        Integer[] exp2 = {20, 19, 18, 17, 16, null, null, null, null,
                null, null, null, null, null, null, null};
        assertArrayEquals(exp2, deque.items);

        for (int i = 1; i < 4; i++) {
            deque.removeLast();
        }
        Integer[] exp3 = {20, 19, null, null, null, null, null, null};
        assertArrayEquals(exp3, deque.items);

    }
}
