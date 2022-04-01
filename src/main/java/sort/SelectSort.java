package sort;

/**
 * @Auther:
 * @Date: 2022/3/13 13:02
 * @Description:
 */

public class SelectSort extends SortTemplate{
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length - 1; ++i) {
            int max = i;
            for (int j = i + 1; j < a.length; ++j) {
                if (less(a[max], a[j])) max = j;
            }
            exchange(a, i, max);
        }
    }

    public static void sort(Comparable[] a, int left, int right) {
        for (int i = left; i < right; ++i) {
            int max = i;
            for (int j = i + 1; j <= right; ++j) {
                if (less(a[max], a[j])) max = j;
            }
            exchange(a, i, max);
        }
    }

    public static void main(String[] args) {
        Integer[] a = {4, 8, 6, 5, 9, 10, 5};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
