package sort;


/**
 * @Auther:
 * @Date: 2022/3/26 15:15
 * @Description:
 */

public class HeapSort extends SortTemplate{

    public static void sort (Comparable[] a) {
        int N = a.length - 1;

        for (int k = N / 2; k > 0; --k) {
            sink(a, k, N);
        }

        while (N > 1) {
            exchange(a, 1, N--);
            sink(a, 1, N);
        }
    }

    /**
     * sink element to sort
     * @param a array
     * @param k element of index k in a
     * @param N length of array
     */
    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k < N) {
            int j = k * 2;
            if (j < N && less(a[j], a[j + 1])) j++;
            if (!less(k, j)) break;
            exchange(a, k, j);
            k = j;
        }
    }


    public static void main(String[] args) {
        Integer[] a = {4, 8, 6, 5, 9, 10, 5};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
