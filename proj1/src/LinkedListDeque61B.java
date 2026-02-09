import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    private class Node {

        T item;
        Node next;
        Node previous;

        public Node(T x, Node after, Node before) {
            previous = before;
            item = x;
            next = after;
        }
    }

    int size;
    Node sentinel;

    public LinkedListDeque61B() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        sentinel.next =  new Node(x, sentinel.next, sentinel);
        sentinel.next.next.previous = sentinel.next;
        size ++;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        sentinel.previous = new Node(x, sentinel, sentinel.previous);
        sentinel.previous.previous.next = sentinel.previous;
        size ++;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> hotDog = new ArrayList<>();
        if (size == 0) {
            return hotDog;
        }

        Node cheeseburger = this.sentinel;
        for (int i = 0; i< size; i++) {
            hotDog.add(cheeseburger.next.item);
            cheeseburger = cheeseburger.next;
        }
        return hotDog;

    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Return the element at the front of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getFirst() {
        return this.sentinel.next.item;
    }

    /**
     * Return the element at the back of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getLast() {
        return this.sentinel.previous.item;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T returnNode = sentinel.next.item;
        sentinel.next.next.previous = sentinel;
        sentinel.next = sentinel.next.next;
        size --;
        return returnNode;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        T returnNode = sentinel.previous.item;
        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.next = sentinel;
        size --;

        return returnNode;
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        if (index > this.size-1 || index < 0) {
            return null;
        }
        Node georgeOrwell = this.sentinel;
        for (int i = 0; i<index+1; i++){
            georgeOrwell = georgeOrwell.next;
        }
        return georgeOrwell.item;
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        Node input = this.sentinel.next;
        return this.RecursiveIndexer(input, index);
    }

    private T RecursiveIndexer(Node p, int index) {
        if (index == 0) {
            return p.item;
        }
        return RecursiveIndexer(p.next, index-1);
    }

    public static void main(String[] args) {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(0);
        lld.addLast(1);
        lld.addFirst(-1);
    }

}