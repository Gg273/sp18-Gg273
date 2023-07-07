public class LinkedListDeque<Luffy> {
    public class DequeNode {
        public DequeNode prev;
        public Luffy item;
        public DequeNode next;

        public DequeNode() {
            prev = null;
            next = null;
        }
        public DequeNode(Luffy i) {
            item = i;
            prev = null;
            next = null;
        }
    }
    private DequeNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new DequeNode();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

//    public LinkedListDeque(Luffy item) {
//        sentinel = new DequeNode();
//        sentinel.next = sentinel;
//        sentinel.prev = sentinel;
//        DequeNode temp = new DequeNode(item);
//        temp.next = sentinel.next;
//        temp.prev = sentinel;
//        temp.next.prev = temp;
//        sentinel.next = temp;
//        size = 1;
//    }

    /**
     * add item to the front of the deque.
     * */
    public void addFirst(Luffy item) {
        DequeNode temp = new DequeNode(item);
        temp.next = sentinel.next;
        temp.prev = sentinel;
        temp.next.prev = temp;
        sentinel.next = temp;
        size += 1;
    }

    /**
     * add item to the back of the deque.
     * */
    public void addLast(Luffy item) {
        DequeNode temp = new DequeNode(item);
        temp.next = sentinel;
        temp.prev = sentinel.prev;
        temp.prev.next = temp;
        sentinel.prev = temp;
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
        DequeNode node = sentinel.next;
        while (node != sentinel) {
            System.out.print(node.item + " ");
            node = node.next;
        }
    }

    public Luffy removeFirst() {
        if (size() == 0) {
            return null;
        }
        DequeNode first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;

        size -= 1;

        return first.item;
    }

    public Luffy removeLast() {
        if (size() == 0) {
            return null;
        }
        DequeNode last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;

        size -= 1;

        return last.item;
    }

    public Luffy get(int index) {
        if (size() <= index) {
            return null;
        }
        DequeNode node = sentinel.next;
        int count = 0;
        while (count < index) {
            node = node.next;
            count += 1;
        }
        return node.item;
    }

    public Luffy getRecursive(int index) {
        if (size() <= index) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private Luffy getRecursiveHelper(DequeNode node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, index);
    }

}
