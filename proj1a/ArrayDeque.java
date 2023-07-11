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

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[INITCAPACITY];
        nextFirst = INITCAPACITY - 1;
        nextLast = 0;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            T temp = (T) other.get(i);
            this.addLast(temp);
        }

    }

    /**
     * resize the capacity of the deque.
     * maybe use the System.arraycopy method will run faster than copy item by item.
     * because it copy a piece of memory to the destine a time( guess? i'm not sure about it.).
     * */
    private void resize(int newCapacity) {
        /*
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
        }*/
        resizeHelper(newCapacity);
    }

    /**
     * a better way to resize the capacity of the deque.
     * */
    private void resizeHelper(int newCapacity) {
        T[] temp = (T[]) new Object[newCapacity];
        int firstIndex = getIndex(nextFirst + 1);
        for (int i = 0; i < size; i++) {
            temp[i] = items[getIndex(firstIndex + i)];
        }
        nextLast = size;
        nextFirst = newCapacity - 1;
        items = temp;
    }

    private int getIndex(int index) {
        return (index + items.length) % items.length;
    }

    /**
     * for test method that return a array that from the first to last;
     * */
    public T[] getItems() {
        T[] temp = (T[]) new Object[size];
        int firstIndex = getIndex(nextFirst + 1);
        for (int i = 0; i < size; i++) {
            temp[i] = items[getIndex(firstIndex + i)];
        }

        return temp;
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
     * print all items in the deque, a bad idea.
     * */
    public void printDeque() {
        /*
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
        */
        printDequeNicer();
    }

    private void printDequeNicer() {
        int first = getIndex(nextFirst + 1);
        for (int i = 0; i < size; i++) {
            System.out.print(items[getIndex(first + i)] + new String(""));
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        double usageRatio = ((double) size) / items.length;
        if (items.length > INITCAPACITY && usageRatio < MINFACTOR) {
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

        double usageRatio = ((double) size) / items.length;
        if (items.length > INITCAPACITY && usageRatio < MINFACTOR) {
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
