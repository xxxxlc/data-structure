package priority_queue;

import func.Print;

/**
 * @Auther:
 * @Date: 2022/3/22 18:55
 * @Description:
 */

public class MaxPQArrayMultiForkHeap<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;
    private int tail;
    private int fork;

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exchange(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && less((k + fork - 2) / fork, k)) {
            exchange((k + fork - 2) / fork, k);
            k = (k + fork - 2) / fork;
        }
    }

    private void sink(int k) {
        while (k * fork < N) {
            int j = k * fork;
            int i = 0;
            int max_idx = j;
            while (i < fork) {
                if (j + i - 1 < N && less(max_idx, j + i - 1)) max_idx = j + i - 1;
                i++;
            }
            j = max_idx;
            if (!less(k, j)) break;
            exchange(k, j);
            k = j;
        }
    }

    public MaxPQArrayMultiForkHeap(Key[] a, int d) {
        pq = (Key[]) new Comparable[a.length + 5];
        tail = a.length + 5;
        fork = d;
        for (int i = 0; i < a.length; ++i) {
            insert(a[i]);
        }
    }

    public MaxPQArrayMultiForkHeap(int maxN, int d) {
        pq = (Key[]) new Comparable[maxN];
        tail = maxN;
        fork = d;
    }

    public void insert(Key v) {
        /**
         * put a element into priority queues
         */
        pq[++N] = v;
        swim(N);

        if (N > tail - 2) {
            Key[] new_pq = (Key[]) new Comparable[pq.length * 2];
            System.arraycopy(pq, 0, new_pq, 0, tail);
            pq = new_pq;
            tail = pq.length * 2;
        }
    }

    public Key max() {
        /**
         * return max element of priority queues
         */
        return pq[1];
    }

    public Key delMax() {
        /**
         * delete and return max element of priority queues
         */
        Key max = pq[1];
        exchange(1, N--);
        pq[N + 1] = null;
        sink(1);

        if (tail > N / 2 + 1) {
            Key[] new_pq = (Key[]) new Comparable[pq.length / 2];
            System.arraycopy(pq, 0, new_pq, 0, N);
            pq = new_pq;
            tail = pq.length / 2;
        }

        return max;
    }

    public boolean isEmpty() {
        /**
         * return whether the queue is empty
         */
        return N == 0;
    }

    public int size() {
        /**
         * return number of element in priority queues
         */
        return N;
    }

    public void print_pq() {
        for (int i = 0; i < pq.length; ++i) {
            Print.printwt(pq[i]);
        }
        Print.print();
    }

    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 6, 5, 8, 9, 7};
        MaxPQArrayMultiForkHeap pq = new MaxPQArrayMultiForkHeap(array, 3);
        pq.insert(5);
        pq.insert(5);
        pq.insert(5);
        pq.insert(5);
        pq.insert(5);
        pq.print_pq();
        Print.print(pq.max());
        Print.print(pq.delMax());
        pq.print_pq();
    }
}
