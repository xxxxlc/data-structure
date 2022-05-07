package string;

/**
 * @Auther:
 * @Date: 2022/4/24 09:42
 * @Description:
 */

public class LSD {

    private static String[] aux;
    private static int[] count;
    private static int R = 256;

    /**
     * use front W char to sort
     * @param a
     * @param W
     */
    public static void sort(String[] a, int W) {
        aux = new String[a.length];
        count = new int[R + 1];

        for (int d = W - 1; d >= 0; --d) {
            for (String s : a) {
                count[s.charAt(d) + 1]++;
            }

            for (int r = 0; r < R; ++r) {
                count[r + 1] += count[r];
            }

            for (String s : a) {
                aux[count[s.charAt(d)]++] = s;
            }

            for (int i = 0; i < a.length; ++i) {
                a[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) {
    }
}
