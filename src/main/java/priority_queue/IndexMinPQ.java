package priority_queue;

/**
 * @Auther:
 * @Date: 2022/3/22 19:27
 * @Description: index priority queue
 */

public class IndexMinPQ<Key extends Comparable<Key>> {
    // number of element in priori queue
    private int N;
    // Index Binary Heap, from 1
    private int[] pq;
    // Reverse Index Binary Heap
    // qp[pq[i]] = pq[qp[i]] = i
    private int[] qp;
    // elements that have priori
    private Key[] keys;

    private boolean less (int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exchange (int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim (int k) {
        while(k > 1 && !less(k / 2, k)) {
            exchange(k / 2, k);
            k = k / 2;
        }
    }

    private void sink (int k) {
        while (2 * k < N) {
            int j = 2 * k;
            if (j  < N && !less(j, j + 1)) j++;
            if (less(k, j)) break;
            exchange(k, j);
            k = j;
        }
    }

    public IndexMinPQ (int maxN) {
        /**
         * initialize function
         */
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; ++i) qp[i] = -1;
    }

    public IndexMinPQ (Key[] a) {
        /**
         * initialize with array
         */
        keys = (Key[]) new Comparable[a.length + 1];
        pq = new int[a.length + 1];
        qp = new int[a.length + 1];
        for (int i = 0; i <= a.length; ++i) qp[i] = -1;

        for (int i = 0; i < a.length; ++i) {
            insert(i, a[i]);
        }
    }

    public void insert (int k, Key key) {
        /**
         * insert a element with index k
         */
        N++;
        qp[k] = N;
        pq[N] = k;
        keys[k] = key;
        swim(N);
    }

    public void change (int k, Key key) {
        /**
         * set element to item of index k
         */
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }

    public boolean contains (int k) {
        /**
         * whether there is a element of index k
         */
        return qp[k] != -1;
    }

    public void delete (int k) {
        /**
         * delete element of index k
         */
        int index = qp[k];
        exchange(index, N--);
        swim(index);
        sink(index);
        keys[k] = null;
        qp[k] = -1;
    }

    public Key min () {
        /**
         * return minimal element
         */
        return keys[pq[1]];
    }

    public int minIndex () {
        /**
         * return index of minimal element
         */
        return pq[1];
    }

    public int delMin () {
        /**
         * delete minimal element and return its index
         */
        int indexOfMin = pq[1];
        exchange(1, N--);
        sink(1);
        keys[pq[N + 1]] = null;
        qp[pq[N + 1]] = -1;
        return indexOfMin;
    }

    public boolean isEmpty () {
        /**
         * whether priori queue is empty
         */
        return N == 0;
    }

    public int size () {
        /**
         * return priori queue size
         */
        return N;
    }

    public static void main(String[] args) {
        Integer[] a = {5, 4 ,7 ,2 ,3 ,1};
        IndexMinPQ pq = new IndexMinPQ(a);

    }
}
