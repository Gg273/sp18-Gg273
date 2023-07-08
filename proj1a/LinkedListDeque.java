public class LinkedListDeque<T> {
    private class DequeNode {
        private DequeNode prev;
        private T item;
        private DequeNode next;

        public DequeNode() {
            prev = null;
            next = null;
        }
        public DequeNode(T i) {
            item = i;
            prev = null;
            next = null;
        }

        public void setPrev(DequeNode p) {
            prev = p;
        }

        public void setNext(DequeNode n) {
            next = n;
        }

        public DequeNode getPrev() {
            return prev;
        }

        public DequeNode getNext() {
            return next;
        }

        public T getItem() {
            return item;
        }
    }
    private DequeNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new DequeNode();
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
        size = 0;
    }

//    public LinkedListDeque(T item) {
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
    public void addFirst(T item) {
        DequeNode temp = new DequeNode(item);
        temp.setNext(sentinel.next);
        temp.setPrev(sentinel);
        temp.getNext().setPrev(temp);
        sentinel.setNext(temp);
        size += 1;
    }

    /**
     * add item to the back of the deque.
     * */
    public void addLast(T item) {
        DequeNode temp = new DequeNode(item);
        temp.setNext(sentinel);
        temp.setPrev(sentinel.getPrev());
        temp.getPrev().setNext(temp);
        sentinel.setPrev(temp);
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
        DequeNode node = sentinel.getNext();
        while (node != sentinel) {
            System.out.print(node.getItem() + new String(" "));
            node = node.getNext();
        }
    }

    public T removeFirst() {
        if (size() == 0) {
            return null;
        }
        DequeNode first = sentinel.getNext();
        sentinel.setNext(first.getNext());
        first.getNext().setPrev(sentinel);

        size -= 1;

        return first.getItem();
    }

    public T removeLast() {
        if (size() == 0) {
            return null;
        }
        DequeNode last = sentinel.getPrev();
        sentinel.setPrev(last.getPrev());
        last.getPrev().setNext(sentinel);

        size -= 1;

        return last.getItem();
    }

    public T get(int index) {
        if (size() <= index) {
            return null;
        }
        DequeNode node = sentinel.getNext();
        int count = 0;
        while (count < index) {
            node = node.getNext();
            count += 1;
        }
        return node.getItem();
    }

    public T getRecursive(int index) {
        if (size() <= index) {
            return null;
        }
        return getRecursiveHelper(sentinel.getNext(), index);
    }

    private T getRecursiveHelper(DequeNode node, int index) {
        if (index == 0) {
            return node.getItem();
        }
        return getRecursiveHelper(node.getNext(), index-1);
    }

}
