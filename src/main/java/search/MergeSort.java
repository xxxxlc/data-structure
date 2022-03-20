package search;

import java.lang.reflect.Array;

/**
 * @Auther:
 * @Date: 2022/3/15 19:32
 * @Description:
 */

public class MergeSort extends SortTemplate{
    public static Comparable[] aux;

    public static void sort_fu(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
        sort(a, 0, a.length - 1, 15);
    }

    public static void sort(Comparable[] a, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        sort(a, left, mid);
        sort(a, mid + 1, right);

        if (less(a[mid], a[mid + 1])) {
            merge(a, left, mid, right);
        }
    }

    public static void sort(Comparable[] a, int left, int right, int len) {
        /**
         * use select sort when array of "a" is more small than len
         */
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        if (mid - left < len) {
            SelectSort.sort(a, left, mid);
            SelectSort.sort(a, mid + 1, right);
        }
        else {
            sort(a, left, mid, len);
            sort(a, mid + 1, right, len);
        }
        if (less(a[mid], a[mid + 1])) {
            merge(a, left, mid, right);
        }
    }

    public static void merge(Comparable[] a, int left, int mid, int right) {
        /*
        put two parts into sorted
         */
        int i = left;
        int j = mid + 1;


        for (int k = left; k <= right; ++k) {
            aux[k] = a[k];
        }

        for (int k = left; k <= right; ++k) {
            if (i > mid) a[k] = aux[j++];
            else if (j > right) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void sort_fd(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[a.length];

        for (int i = 1; i < N; i = i + i) {
            for (int j = 0; j < N - i; j += i + i) {
                merge(a, j, j + i - 1, Math.min(j + 2 * i - 1, N - 1));
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {4, 8, 6, 5, 9, 10, 5};
        sort_fd(a);
        assert isSorted(a);
        show(a);
    }
}
