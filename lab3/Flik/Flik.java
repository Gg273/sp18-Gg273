/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        /**
         * I don't why the compare result will be false when a and b both greater equal to 128,
         * but this is the error, and you can change it to a.equal(b).
         * After search in stackoverflow about Integer for few minutes.
         * The Integer is a reference type, and when use the range between -128 to 127, the Integer will
         * return a default set instance while running the java code; otherwise, will create a new instance, and this
         * is comparing the two reference instances, ex: new Integer(128) == new Integer(128) and will be false.
         * and could be use -Djava.lang.Integer.IntegerCache.high=999 the system property to change the default range.
         * the better way is use the equals() method. ex: a.equals(b);
         * */
        return a.equals(b);
        //return a == b;
    }
}
