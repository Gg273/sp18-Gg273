public class ArrayDeque<T> {
    // need to change to public when using the ArrayDequeTest
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private static final int INITCAPACITY = 8;
    private static final double MINFACTOR = 0.25;
    private static final int RFACTOR = 2;

    public ArrayDeque() {
        items = (T[]) new Object[INITCAPACITY];
        nextFirst = INITCAPACITY - 1;
        nextLast = 0;
        size = 0;
    }

    /**
     * resize the capacity of the deque.
     * */
    private void resize(int newCapacity) {
        int currentFirstIndex = getIndex(nextFirst + 1);
        int currentLastIndex = getIndex(nextLast - 1);
        if (newCapacity < items.length && currentFirstIndex < currentLastIndex) {
            int totalPieceIndex = currentFirstIndex;
            int totalPieceLenght = currentLastIndex - currentFirstIndex + 1;

            T[] temp = (T[]) new Object[newCapacity];
            System.arraycopy(items, totalPieceIndex, temp, 0, totalPieceLenght);
            nextLast = totalPieceLenght;
            nextFirst = newCapacity - 1;
            items = temp;
        } else {
            int firstPieceIndex = currentFirstIndex;
            int firstPieceLength = items.length - currentFirstIndex;
            int lastPieceIndex = 0;
            int lastPieceLength = nextLast;

            T[] temp = (T[]) new Object[newCapacity];
            System.arraycopy(items, lastPieceIndex, temp, 0, lastPieceLength);
            System.arraycopy(items, firstPieceIndex, temp, newCapacity - firstPieceLength, firstPieceLength);
            nextFirst = newCapacity - firstPieceLength - 1;
            items = temp;
        }
    }

    private int getIndex(int index) {
        return (index + items.length) % items.length;
    }

    /**
     * add item to the front of the deque.
     * */
    public void addFirst(T item) {
        if (items.length == size) {
            resize(items.length * RFACTOR);
        }
        items[nextFirst] = item;
        nextFirst = getIndex(nextFirst - 1);
        size += 1;
    }

    /**
     * add item to the back of the deque.
     * */
    public void addLast(T item) {
        if (items.length == size) {
            resize(items.length * RFACTOR);
        }
        items[nextLast] = item;
        nextLast = getIndex(nextLast + 1);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the size of the deque
     * */
    public int size() {
        return size;
    }

    /**
     * print all items in the deque
     * */
    public void printDeque() {
        int currentFirstIndex = getIndex(nextFirst + 1);
        int currentLastIndex = getIndex(nextLast - 1);

        if (size < items.length && currentFirstIndex < currentLastIndex) {
            for (int i = currentFirstIndex; i < (currentLastIndex + 1); i++) {
                System.out.println(items[i] + new String(" "));
            }
        } else {
            for (int i = currentFirstIndex; i < items.length; i++) {
                System.out.println(items[i] + new String(" "));
            }
            for (int i = 0; i < (currentLastIndex + 1); i++) {
                System.out.println(items[i] + new String(" "));
            }
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        double factor = ((double) size) / items.length;
        if (items.length > INITCAPACITY && factor < MINFACTOR) {
            resize(items.length / RFACTOR);
        }

        nextFirst = getIndex(nextFirst + 1);
        T temp = items[nextFirst];
        items[nextLast] = null;
        size -= 1;
        return temp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        double factor = ((double) size) / items.length;
        if (items.length > INITCAPACITY && factor < MINFACTOR) {
            resize(items.length / RFACTOR);
        }

        nextLast = getIndex(nextLast - 1);
        T temp = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return temp;
    }

    public T get(int index) {
        if (size < index) {
            return null;
        }
        int i = getIndex(nextFirst + 1);
        for (int count = 0; count < index; count++) {
            i = getIndex(i + 1);
        }
        return items[i];
    }
}
