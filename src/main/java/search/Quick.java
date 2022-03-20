package search;

import java.util.Arrays;
import java.util.Random;

/**
 * @Auther:
 * @Date: 2022/3/16 20:03
 * @Description:
 */

public class Quick extends SortTemplate {
    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }


    public static void shuffle(Comparable[] a) {
        Random random = new Random();
        int len = a.length;
        for (int i = 0; i < len - 1; ++i) {
            int j = random.nextInt(len - i) + i;
            exchange(a, i, j);
        }
    }


    public static void sort(Comparable[] a, int left, int right) {
        if (left >= right) return;
        int j = partition(a, left, right);
        sort(a, left, j - 1);
        sort(a, j + 1, right);
    }

    public static void sort_insert(Comparable[] a, int left, int right, int M) {
        /**
         * when array is small, switch to InsertSort
         */
        if (left >= right) return;
        if (left + M > right) {
            InsertSort.sort(a, left, right);
            return;
        }
        int j = partition(a, left, right);
        sort(a, left, j - 1);
        sort(a, j + 1, right);
    }


    public static int partition(Comparable[] a, int left, int right) {
        int i = left;
        int j = right + 1;
//        Comparable v = a[left];
        int mid = find_mid(a, left, right);
        Comparable v = a[mid];
        exchange(a, left, mid);

        while (true) {
            while(!less(a[++i], v)) if (i == right) break;
            while(!less(v, a[--j])) if (j == left) break;
            if (i >= j) break;
            exchange(a, i, j);
        }
        exchange(a, left, j);
        return j;
    }


    public static int find_mid(Comparable[] a, int left, int right) {
        /**
         * Randomly find five numbers and use the median as the dividing number
         */
        if (right - left <= 5) return left;
        Random random = new Random();

        Integer[] rand_arr = new Integer[5];

        Arrays.fill(rand_arr, -1);

        int i = 0;
        while(i < 5) {
            int rand_num = random.nextInt(right - left) + left;
            if (contains(rand_arr, rand_num)) {
                rand_arr[i++] = rand_num;
            }
        }

        int mid;
        for (int j = 1; j < rand_arr.length; ++j) {
            for (int k = 0; k < j; ++k) {
                if (a[rand_arr[k]].compareTo(a[rand_arr[j]]) < 0) {
                    exchange(rand_arr, k, j);
                }
            }
        }

        return rand_arr[2];
    }


    public static boolean contains(Comparable[] arr, Comparable num) {
        for (Comparable i : arr) {
            if (i.compareTo(num) == 0) return false;
        }
        return true;
    }


    public static void sort_three_split(Comparable[] a, int left, int right) {
        /**
         * three split array: a[left...lt] <= v = a[lt...gt] <= a[gt+1...right]
         */
        if (left >= right) return;
        int lt = left, i = left + 1, gt = right;
        Comparable v = a[left];

        while(i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp > 0) exchange(a, i++, lt++);
            else if (cmp < 0) exchange(a, i, gt--);
            else i++;
        }
        sort_three_split(a, left, lt - 1);
        sort_three_split(a, gt + 1, right);
    }

    public static void main(String[] args) {
        Integer[] a = {4, 8, 6, 5, 9, 10, 5};
//        sort_three_split(a, 0, 6);
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
