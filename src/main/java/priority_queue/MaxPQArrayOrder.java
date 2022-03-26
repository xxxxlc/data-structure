package priority_queue;

import func.Print;
import search.InsertSort;

/**
 * @Auther:
 * @Date: 2022/3/20 20:16
 * @Description: Ordered array implements priority queue
 */

public class MaxPQArrayOrder<Key extends Comparable<Key>> {
    public Comparable[] stack;
    public int tail;

    MaxPQArrayOrder() {
        stack = new Comparable[10];
        tail = 8;
    }

    MaxPQArrayOrder(int max) {
        stack = new Comparable[max];
        tail = 0;
    }

    MaxPQArrayOrder(Key[] a) {
        stack = new Comparable[a.length + a.length / 4];
        InsertSort insert_sort = new InsertSort();
        insert_sort.sort(a);
        insert_sort.reverse(a);
        System.arraycopy(a, 0, stack, 0, a.length);
        tail = a.length;
    }

    public void insert(Key v) {
        /**
         * put a element into priority queues
         */
        int idx = tail - 1;
        while (stack[idx].compareTo(v) > 0) {
            stack[idx + 1] = stack[idx];
            idx--;
        }
        stack[idx + 1] = v;
        tail++;

        if (tail > stack.length * 0.8) {
            Comparable[] new_stack = new Comparable[stack.length + stack.length / 4];
            System.arraycopy(stack, 0, new_stack, 0, tail);
            stack = new_stack;
        }
    }

    public Key max() {
        /**
         * return max element of priority queues
         */
        return (Key) stack[tail - 1];
    }

    public Key delMax() {
        /**
         * delete and return max element of priority queues
         */
        tail --;
        return (Key) stack[tail - 1];
    }

    public boolean isEmpty() {
        return tail == 0;
    }

    public int size() {
        return tail;
    }

    public void print_pq() {
        for (int i = 0; i < tail; ++i) {
            Print.printwt(stack[i]);
        }
        Print.print();
    }

    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 6, 5, 8, 9, 7};
        MaxPQArrayOrder pq = new MaxPQArrayOrder(array);
        pq.insert(5);
        pq.print_pq();
        Print.print(pq.max());
        Print.print(pq.delMax());
        pq.print_pq();

    }
}
