package basic_datastructure;

import java.util.Iterator;

/**
 * @Auther:
 * @Date: 2022/3/30 19:26
 * @Description:
 */

public class Stack<Item> implements Iterable<Item> {

    // number of element
    public int N = 0;
    // stack
    public Item[] stack;

    public boolean isFull() {
        return !(N < stack.length);
    }

    public Stack () {
        stack = (Item[]) new Object[10];
    }

    public Stack (int max) {
        stack = (Item[]) new Object[max];
    }

    public void push (Item item) {
        if (isFull()) throw new IllegalArgumentException("stack is full");
        stack[N++] = item;
    }

    public Item pop () {
        if (isEmpty()) throw new IllegalArgumentException("stack is empty");
        Item temp = stack[--N];
        stack[N+1] = null;
        return temp;
    }

    public boolean isEmpty () {
        return N == 0;
    }

    public int size () {
        return N;
    }


    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    public class StackIterator implements Iterator<Item> {

        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return stack[--i];
        }
    }



    public static void main(String[] args) {
    }

}
