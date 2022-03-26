package search;
import static func.Print.print;
import static func.Print.printnb;

/**
 * @Auther: xxxxlc
 * @Date: 2022/3/13 11:33
 * @Description:
 */

public class SortTemplate{
    public static void sort(Comparable[] a) {

    }

    public static boolean less(Comparable v, Comparable w) {
        /**
         * @return: is v < w ?
         */
        return v.compareTo(w) < 0;
    }

    public static void exchange(Comparable[] a, int i, int j) {
        /**
         * exchange two elements in array
         */
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean isSorted(Comparable[] a) {
        /**
         * is the array sorted
         */
        for (int i = 1; i < a.length; ++i) {
            if (less(a[i], a[i - 1]))  return false;
        }
        return true;
    }

    public static void show(Comparable[] a) {
        for (Comparable t : a) {
            printnb(t + " ");
        }
        print();
    }

    public static void reverse(Comparable[] a) {
        for (int i = 0; i < a.length / 2; ++i) {
            Comparable tmp = a[i];
            a[i] = a[a.length - 1 - i];
            a[a.length - 1 - i] = tmp;
        }
    }

    public static void main(String[] args) {
        Integer[] a = {4, 8, 6, 5};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
