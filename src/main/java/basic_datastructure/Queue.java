package basic_datastructure;

import java.util.Iterator;

import static func.Print.print;

/**
 * @Auther:
 * @Date: 2022/3/30 18:41
 * @Description: simple queue
 */

public class Queue<Item> implements Iterable<Item> {

    // head of queue
    public int head = 0;
    // tail of queue
    public int tail = 0;
    // queue
    public Item[] queue;

    public boolean isFull() {
        return !(tail < queue.length);
    }

    public Queue () {
        queue = (Item[]) new Object[10];
    }

    public Queue (int max) {
        queue = (Item[]) new Object[max];
    }

    public void enqueue (Item item) {
        if (isFull()) throw new IllegalArgumentException("queue is full");
        queue[tail++] = item;
    }

    public Item dequeue () {
        Item temp = queue[head];
        queue[head] = null;
        head++;
        return temp;
    }

    public boolean isEmpty () {
        return head == tail;
    }

    public int size () {
        return tail - head;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    public class QueueIterator implements Iterator<Item> {

        private int i = head;
        private int j = tail;

        @Override
        public boolean hasNext() {
            return j - i > 0;
        }

        @Override
        public Item next() {
            return queue[i++];
        }
    }

    public static void main(String[] args) {
        Queue<Integer> numbers = new Queue<Integer>();
        numbers.enqueue(1);
        numbers.enqueue(2);
        numbers.enqueue(3);
        numbers.dequeue();
        for (int e:numbers) {
            print(e);
        }
    }
}
