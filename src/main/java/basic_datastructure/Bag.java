package basic_datastructure;

import java.util.Iterator;

import static func.Print.print;

/**
 * @Auther:
 * @Date: 2022/3/29 18:38
 * @Description: simple bag
 */

public class Bag<Item> implements Iterable<Item> {

    // number of element
    public int N = 0;
    // bag
    public Item[] bag;

    public boolean isFull() {
        return !(N < bag.length);
    }

    public Bag() {
        bag = (Item[]) new Object[10];
    }

    public Bag (int cap) {
        bag = (Item[]) new Object[cap];
    }

    public void add (Item item) {
        if (isFull()) throw new IllegalArgumentException("bag is full");
        bag[N++] = item;
    }

    public boolean isEmpty () {
        return N == 0;
    }

    public int size () {
        return N;
    }

    @Override
    public Iterator<Item> iterator() {
        return new BugIterator();
    }

    public class BugIterator implements Iterator<Item> {

        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return bag[--i];
        }
    }

    public static void main(String[] args) {
        Bag<Integer> numbers = new Bag<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        for (int e:numbers) {
            print(e);
        }
    }
}
