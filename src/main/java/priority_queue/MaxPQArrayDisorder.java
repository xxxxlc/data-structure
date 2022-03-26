package priority_queue;

import func.Print;

/**
 * @Auther:
 * @Date: 2022/3/20 15:55
 * @Description: Disordered array implements priority queue
 */

public class MaxPQArrayDisorder<Key extends Comparable<Key>> {
    public Comparable[] stack;
    public int tail;

    MaxPQArrayDisorder() {
        stack = new Comparable[10];
        tail = 8;
    }

    MaxPQArrayDisorder(int max) {
        stack = new Comparable[max];
        tail = 0;
    }

    MaxPQArrayDisorder(Key[] a) {
        stack = new Comparable[a.length + a.length / 4];
        System.arraycopy(a, 0, stack, 0, a.length);
        tail = a.length;
    }

    public void insert(Key v) {
        /**
         * put a element into priority queues
         */
        stack[tail++] = v;

        if (tail > stack.length * 0.8) {
            Comparable[] new_stack = new Comparable[stack.length + stack.length / 4];
            System.arraycopy(stack, 0, new_stack, 0, tail);
            stack = new_stack;
        }
    }

    public Key max() {
        Comparable max_element = 0;
        int max_idx = 0;
        for (int i = 0; i < tail; ++i) {
            if (max_element.compareTo(stack[i]) < 0) {
                max_element = stack[i];
                max_idx = i;
            }
        }

        Comparable temp = max_element;
        stack[max_idx] = stack[tail - 1];
        stack[tail - 1] = temp;

        return (Key) max_element;
    }

    public Key delMax() {
        Comparable max_element = this.max();
        stack[tail--] = 0;
        return (Key) max_element;
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
        Integer[] array = {1, 2, 3, 4};
        MaxPQArrayDisorder pq = new MaxPQArrayDisorder(array);
        pq.insert(5);
        Print.print(pq.max());
        pq.delMax();
        Print.print(pq.max());
    }
}
