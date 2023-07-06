import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class HorribleSteve {

    @Test
    public void testIsSameNumber() {
        int i = 126;
        int j = 126;
        assertTrue(Flik.isSameNumber(i, j));
        i = 127;
        j = 127;
        assertTrue(Flik.isSameNumber(i, j));
        i = 129;
        j = 129;
        assertTrue(Flik.isSameNumber(i, j));
    }
    public static void main(String [] args) {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                break; // break exits the for loop!
            }
        }
        System.out.println("i is " + i);
    }
}
