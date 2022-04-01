package sort;

/**
 * @Auther: xxxxlc
 * @Date: 2022/3/14 11:12
 * @Description: Shell sort
 */

public class Shell extends SortTemplate {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        int interval = 3;
        while (h < N) {
            h = h * interval + 1;
        }

        while (h >= 1) {
            for (int i = h; i < N; ++i) {
                for (int j = i; j >= h; j = j - h) {
                    if (!less(a[j], a[j - h])) exchange(a, j, j - h);
                }
            }
            h = h / interval;
        }
    }

    public static void main(String[] args) {
        Integer[] a = {4, 8, 6, 5, 9, 10, 5, 7};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
