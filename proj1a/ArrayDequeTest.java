import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void resizeTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
        for (int i = 1; i < 10; i++) {
            deque.addFirst(i);
        }
        Integer[] exp = {null, null, null, null, null, null, null, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        assertArrayEquals(exp, deque.items);

        for (int i = 0; i > -10; i--) {
            deque.addLast(i);
        }
        Integer[] exp1 = {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, null, null, null, null, null, null, null, null, null, null, null, null, null, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        assertArrayEquals(deque.items, exp1);

        for (int i = 10; i < 15; i++) {
            deque.addFirst(i);
        }

        for (int i = 0; i < 20; i++) {
            deque.removeLast();
        }
        Integer[] exp2 = {null, 14, 13, 12, 11, null, null, null, null, null, null, null, null, null, null, null};
        assertArrayEquals(deque.items, exp2);

    }
}
