package basic_datastructure;

import java.util.Iterator;

/**
 * @Auther:
 * @Date: 2022/3/26 20:17
 * @Description:
 */

public class ResizingArrayStack<Item> implements Iterable<Item> {

    // element of stack
    private Item[] a = (Item[]) new Object[1];

    // number of element in stack
    private int N = 0;

    public boolean isEmpty () {
        return N == 0;
    }

    public int size () {
        return N;
    }

    /**
     * put stack into a new stack with size of max
     * @param max size of the new stack
     */
    private void resize (int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < max; ++i) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push (Item item) {
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    public Item pop() {
        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length / 4) resize(a.length / 2);
        return item;
    }


    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;
        public boolean hasNext() {return i > 0;}
        public Item next () {return a[--i];}
        public void remove () {}
    }


    public static void main(String[] args) {

    }
}
