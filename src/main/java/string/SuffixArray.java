package string;

/**
 * @Auther:
 * @Date: 2022/5/7 11:51
 * @Description:
 */

public class SuffixArray {

    // suffix array
    private final String[] suffixes;

    // length of string
    private final int N;

    private static int lcp(String s, String t) {
        int n = Math.min(s.length(), t.length());
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) != t.charAt(i)) return i;
        }
        return n;
    }

    public SuffixArray(String s) {
        N = s.length();
        suffixes = new String[N];
        for (int i = 0; i < N; ++i) {
            suffixes[i] = s.substring(i);
        }
        Quick3string.sort(suffixes);
    }

    public int length() {
        return N;
    }

    public String select(int i) {
        return suffixes[i];
    }

    public int index(int i) {
        return N - suffixes[i].length();
    }

    public int lcp(int i) {
        return lcp(suffixes[i], suffixes[i - 1]);
    }

    public int rank(String key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(suffixes[mid]);
            if (cmp < 0) {
                hi = mid + 1;
            }
            else if (cmp > 0) {
                lo = mid + 1;
            }
            else return mid;
        }
        return lo;
    }

    public static void main(String[] args) {

    }
}
