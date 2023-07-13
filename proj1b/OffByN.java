public class OffByN implements CharacterComparator{

    private int N;
    OffByN(int N) {
        this.N = N;
    }
    public boolean equalChars(char x, char y) {
        int diff = Math.abs(x - y);
        if (diff == this.N) {
            return true;
        }
        return false;
    }
}
