import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    /*
    * @source example from StudentArrayDequeLauncher.java
    * */
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        int dequeSize = 10;

        String errorMsg = new String("\n");

        for (int i = 0; i < dequeSize; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                ads1.addLast(i);
                errorMsg += "addLast(" + i + ")\n";
            } else {
                sad1.addFirst(i);
                ads1.addFirst(i);
                errorMsg += "addFirst(" + i + ")\n";
            }
        }

        for (int i = 0; i < dequeSize; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            Integer actual = 0;
            Integer expected = 0;

            if (numberBetweenZeroAndOne < 0.5) {
                actual = sad1.removeLast();
                expected = ads1.removeLast();
                errorMsg += "removeLast()\n";
            } else {
                actual = sad1.removeFirst();
                expected = ads1.removeFirst();
                errorMsg += "removeFirst()\n";
            }
            assertEquals(errorMsg, expected, actual);
        }

        //sad1.printDeque();
        //ads1.printDeque();

    }
}
