import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private T[] items;
    private int nextStart;
    private int nextEnd;
    private int size;
    private int arraysize;

    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        nextStart = 2;
        nextEnd = 3;
        size = 0;
        arraysize = 8;
    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    // This method adds a new element to the front of the arrayDeque
    // If the nextStart and nextEnd and the same, then you resize the array.
    //    Shift the indexes to reflect this
    // If the next Start and nextEnd are not the same, place the element at nextEnd
    //    Shift the index to reflect this.
    //    If the nextFirst index becomes -1, then make nextIndex the end of the arrayDeque

    public void addFirst(T x) {
        if (size == arraysize) {
            this.ResizeUp();
        }
        items[nextStart] = x;
        nextStart -= 1;
        if (nextStart == -1) {
            nextStart = arraysize - 1;
        }
        size += 1;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    // This method adds a new element to the back of the arrayDeque
    // If the nextStart and nextEnd and the same, then you resize the array.
    //    Shift the indexes to reflect this
    // If the next Start and nextEnd are not the same, place the element at nextEnd
    //    Shift the index to reflect this.
    //    If the nextEnd index becomes arraysize, then make nextEnd the front of the arrayDeque
    public void addLast(T x) {
        if (size == arraysize) {
            this.ResizeUp();
        }
        items[nextEnd] = x;
        nextEnd += 1;
        if (nextEnd == arraysize) {
            nextEnd = 0;
        }
        size += 1;

    }

    public void ResizeUp() {
        T[] resizedItems = (T[]) new Object[arraysize*2];
        // we start from the index, nextStart + 1;
        // If nextStart + 1 exceeds the size of the array, then index is 0;
        // Based on this start index, we iterate over the index for the number of elements in the array.

        int index = nextStart + 1;
        if (index == arraysize) {
            index = 0;
        }
        for (int i = 0; i < arraysize; i++) {
            resizedItems[(arraysize/2) + i] = this.items[index];
            index += 1;
            if (index == arraysize) {
                index = 0;
            }
        }
        items = resizedItems;
        arraysize *= 2;
        nextStart = (arraysize/4) - 1;
        nextEnd = nextStart + 1 + size;

    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int index = nextStart + 1;
        if (index == arraysize) {
            index = 0;
        }

        for (int i = 0; i < this.size; i++) {
             returnList.add(this.items[index]);
                index += 1;
                if (index == arraysize) {
                    index = 0;
                }
            }

        return returnList;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
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
        if (this.isEmpty()) {
            return null;
        }
        if ((nextStart + 1) == arraysize) {
            return this.items[0];
        }
        return this.items[nextStart+1];
    }

    /**
     * Return the element at the back of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getLast() {
        if (this.isEmpty()) {
            return null;
        }
        if ((nextEnd - 1) == -1) {
            return this.items[arraysize - 1];
        }
        return this.items[nextEnd -1];
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
        int index = nextStart + 1;
        T returnValue;
        if (index == arraysize) {
            index = 0;
        }
        returnValue = this.items[index];
        this.items[index] = null;
        nextStart += 1;
        if (nextStart == arraysize) {
            nextStart = 0;
        }
        size -= 1;
        if (size >= 8 && size <= arraysize/4) {
            this.ResizeDown();
        }
        return returnValue;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int index = nextEnd - 1;
        T returnValue;
        if (index == -1) {
            index = arraysize - 1;
        }
        returnValue = items[index];
        items[index] = null;
        nextEnd -= 1;
        if (nextEnd == -1) {
            nextEnd = arraysize - 1;
        }
        size -= 1;
        if (size >= 8 && size <= arraysize/4) {
            this.ResizeDown();
        }
        return returnValue;


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
        if (index >= size || index < 0) {
            return null;
        }
        int returnIndex = index + nextStart + 1;
        if (returnIndex >= arraysize) {
            returnIndex -= arraysize;
        }

        return items[returnIndex];
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
        throw new UnsupportedOperationException("No need to implement getRecursive for ArrayDeque61B.");
    }

    public void ResizeDown() {
        T[] resizedItems = (T[]) new Object[arraysize/2];

        int index = nextStart + 1;
        if (index == arraysize) {
            index = 0;
        }
        for (int i = 0; i < size; i++) {
            resizedItems[(arraysize/8) + i] = this.items[index];
            index += 1;
            if (index == arraysize/2) {
                index = 0;
            }
        }
        items = resizedItems;
        arraysize /= 2;
        nextStart = (arraysize/4) - 1;
        nextEnd = nextStart + 1 + size;
    }



    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public java.util.Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {

        private int position;
        private int arrayIndex;

        public ArrayDequeIterator() {
            position = 0;
            arrayIndex = nextStart + 1;
            if (arrayIndex == arraysize) {
                arrayIndex = 0;
            }
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return position < size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            if (position == size) {
                return null;
            }
            T returnItem = items[arrayIndex];
            position += 1;
            arrayIndex += 1;
            if (arrayIndex == arraysize) {
                arrayIndex = 0;
            }
            return returnItem;

        }
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

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */

    // We want this method to iterate recursively
    // To iterate recursively, we want the object to be able to return

    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }

        if (other instanceof Deque61B<?> otherArray) {
            if(this.size() != otherArray.size()) {
                return false;
            }
            for (int i = 0; i<this.size(); i++) {
                if(!((otherArray.get(i)).equals(this.get(i)))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder("[");
        for (int i = 0; i<size; i++) {
            returnString.append(this.get(i));
            if (i != size-1) {
                returnString.append(", ");
            }
        }
        returnString.append("]");
        return returnString.toString();

    }
}