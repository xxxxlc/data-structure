package func;
import java.util.Arrays;
import java.util.Random;

/**
 * @Auther:
 * @Date: 2022/3/24 21:26
 * @Description:
 */

public class Shuffle {

    private static Random rand = new Random();

    /**
     * swap two element in array
     *
     * @param a array
     * @param i index 1
     * @param j index 2
     * @param <T> any data struct
     */
    public static <T> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     *
     * @param arr the array need to be shuffled
     * @param <T> generics
     */
    public static <T> void shuffle (T[] arr) {
        int length = arr.length;
        for (int i = length; i > 0; --i) {
            int randInd = rand.nextInt(i);
            swap(arr, i - 1, randInd);
        }
    }

    public static void main(String[] args) {
    }
}
