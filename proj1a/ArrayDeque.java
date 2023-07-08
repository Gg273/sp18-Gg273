public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    final private static int INITCAPACITY = 8;
    final private static double MINFACTOR = 0.25;

    public ArrayDeque() {
        items = (T[]) new Object[INITCAPACITY];
        nextFirst = INITCAPACITY-1;
        nextLast = 0;
        size = 0;
    }

    /**
     * resize the capacity of the deque.
     * */
    private void resize(int newCapacity) {
        //TODO: need to.
        if (newCapacity < items.length && nextFirst < nextLast) {
            T[] temp = (T[]) new Object[newCapacity];
            System.arraycopy(items, nextFirst+1, temp, 1, nextLast-nextFirst-1);
            nextLast = nextLast-nextFirst;
            nextFirst = 0;
            items = temp;
        } else {
            T[] temp = (T[]) new Object[newCapacity];
            System.arraycopy(items, 0, temp, 0, nextLast);
            System.arraycopy(items, nextLast, temp, newCapacity - items.length + getIndex(nextFirst + 1), items.length - getIndex(nextFirst + 1));
            nextFirst = newCapacity - items.length + getIndex(nextFirst + 1) - 1;
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
            resize(items.length * 2);
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
            resize(items.length * 2);
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
        if (size == items.length || nextFirst < nextLast) {
            for (int i = nextFirst + 1; i < items.length; i++) {
                System.out.println(items[i] + new String(" "));
            }
            for (int i = 0; i < nextLast; i++) {
                System.out.println(items[i] + new String(" "));
            }
        } else {
            for (int i = nextFirst+1; i < nextLast; i++) {
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
            resize(items.length / 2);
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
            resize(items.length / 2);
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
        int i = getIndex(nextFirst+1);
        for (int count = 0; count < index; count++) {
            i = getIndex(i + 1);
        }
        return items[i];
    }
}
