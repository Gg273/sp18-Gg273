import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void resizeTest() {

        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
        for (int i = 1; i < 11; i++) {
            deque.addFirst(i);
        }
        Integer[] exp = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        assertArrayEquals(exp, deque.getItems());
        ArrayDeque<Integer> deepCopyDeque = new ArrayDeque<Integer>(deque);
        assertArrayEquals(deque.getItems(), deepCopyDeque.getItems());

        for (int i = 0; i > -10; i--) {
            deque.addLast(i);
        }
        exp = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5, -6, -7, -8, -9};
        assertArrayEquals(exp, deque.getItems());
        deepCopyDeque = new ArrayDeque<Integer>(deque);
        assertArrayEquals(deque.getItems(), deepCopyDeque.getItems());

        for (int i = 11; i < 21; i++) {
            deque.addFirst(i);
        }

        for (int i = 1; i < 26; i++) {
            deque.removeLast();
        }
        exp = new Integer[]{20, 19, 18, 17, 16};
        assertArrayEquals(exp, deque.getItems());
        deepCopyDeque = new ArrayDeque<Integer>(deque);
        assertArrayEquals(deque.getItems(), deepCopyDeque.getItems());

        for (int i = 1; i < 4; i++) {
            deque.removeLast();
        }
        exp = new Integer[]{20, 19};
        assertArrayEquals(exp, deque.getItems());
        deepCopyDeque = new ArrayDeque<Integer>(deque);
        assertArrayEquals(deque.getItems(), deepCopyDeque.getItems());

    }
}
