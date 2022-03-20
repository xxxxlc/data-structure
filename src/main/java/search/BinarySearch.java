//: data_structure/src/search/BinarySearch.java
// BinarySearch implementation
package search;
import java.util.Arrays;
import static func.Print.print;

public class BinarySearch {
    /*
    BinarySearch require the array must be ordered
     */
    public static <T extends Comparable<T>> int search(T key, T[] a) {
        /**
         * @para:
         * key: the number needed to find in array
         * a: array
         * @return:
         * index in array
         */

        // define left and right two point
        int left = 0;
        int right = a.length - 1;

        while(left <= right) {
            int mid = (left + right) / 2;
            if (a[mid].compareTo(key) == 0) {
                return mid;
            }
            else if (a[mid].compareTo(key) > 0) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Integer[] list = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        int ans = search(target, list);
        print(ans);
    }
}
