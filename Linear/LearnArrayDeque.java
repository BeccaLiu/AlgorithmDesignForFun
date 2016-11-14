package Linear;

/**
 * Created by rliu on 11/12/16.
 * Learn the details of the implements of array deque of java
 */
public class LearnArrayDeque {
    private static final int MIN_INITIAL_CAPACITY = 8;
    Object[] elements; // non-private to simplify nested class access
    int head;
    int tail;

    public static void main(String[] args) {
        LearnArrayDeque lad = new LearnArrayDeque();
        lad.allocateElements(21);
        System.out.println(lad.elements.length);

    }

    //TODO: Know the detail?
    private void allocateElements(int numElements) {

        int initialCapacity = MIN_INITIAL_CAPACITY;
        // Find the best power of two to hold elements.
        // Tests "<=" because arrays aren't kept full.
        if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            //initialCapacity>>>1 =initialCapacity/2
            initialCapacity |= (initialCapacity >>> 1);
            initialCapacity |= (initialCapacity >>> 2);
            initialCapacity |= (initialCapacity >>> 4);
            initialCapacity |= (initialCapacity >>> 8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;

            if (initialCapacity < 0)   // Too many elements, must back off
                initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 elements
        }
        elements = new Object[initialCapacity];
    }

    private void doubleCapacity() {
        assert head == tail;
        int p = head;
        int n = elements.length;
        int r = n - p; // number of elements to the right of p
        int newCapacity = n << 1; //new capacity=n*2
        if (newCapacity < 0) //TODO: when will this exception be thrown?
            throw new IllegalStateException("Sorry, deque too big");
        Object[] a = new Object[newCapacity];
        System.arraycopy(elements, p, a, 0, r);
        System.arraycopy(elements, 0, a, r, p);
        elements = a;
        head = 0;
        tail = n;
    }

    public void addFirst(Object e) {
        if (e == null)
            throw new NullPointerException();
        //12&15=12, 17&15=1 , elements.length has to be power of 2
        //length-1->    1111
        //      12->    1100  &-> 1100
        //      17->   10001  &-> 0001
        //      -1->    1111  &-> 1111
        //      -2->  1....11110
        //Integer.toBinaryString(i)
        elements[head = (head - 1) & (elements.length - 1)] = e;
        if (head == tail)
            doubleCapacity();
    }

    public void addLast(Object e) {
        if (e == null)
            throw new NullPointerException();
        elements[tail] = e;
        if ((tail = (tail + 1) & (elements.length - 1)) == head)
            doubleCapacity();
    }
}
