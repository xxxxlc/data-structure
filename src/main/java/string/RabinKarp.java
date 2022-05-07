package string;

/**
 * @Auther:
 * @Date: 2022/5/6 11:20
 * @Description:
 */

public class RabinKarp {

    private String pat;
    private long patHash;
    private int M;
    private long Q;
    private int R = 256;
    private long RM;

    public RabinKarp(String pat) {
        this.pat = pat;
        this.M = pat.length();
        Q = longRondomPrime();
        RM = 1;

        for (int i = 1; i <= M; ++i) {
            RM = (R * RM) % Q;
        }

        patHash = hash(pat, M);
    }

    private long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; ++j) {
            h = (R * h + key.charAt(j)) % Q;
        }
        return h;
    }

    public boolean check(int i) {
        return true;
    }

    private long longRondomPrime() {
        return 997;
    }

    public int search(String txt) {
        int N = txt.length();
        long txthash = hash(txt, M);
        if (patHash == txthash && check(0)) return 0;

        for (int i = M; i < N; ++i) {
            txthash = (txthash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txthash = (txthash * R + txt.charAt(i)) % Q;

            if (patHash == txthash) {
                if (check(i - M + 1)) return i - M + 1;
            }
        }

        return N;
    }

    public static void main(String[] args) {
    }
}
