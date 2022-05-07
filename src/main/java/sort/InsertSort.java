package sort;

/**
 * @Auther:
 * @Date: 2022/3/13 13:52
 * @Description:
 */

public class InsertSort extends SortTemplate {
    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (!less(a[i], a[j])) exchange(a, i, j);
            }
        }
    }

    public static void sort(Comparable[] a, int left, int right) {
        for (int i = left + 1; i <= right; ++i) {
            for (int j = left; j < i; ++j) {
                if (!less(a[i], a[j])) exchange(a, i, j);
            }
        }
    }

    public static void sort(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; ++i) {
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--) {
                exchange(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {4, 8, 6, 5, 9, 10, 5};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
