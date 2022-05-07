package string;

/**
 * @Auther:
 * @Date: 2022/5/6 10:06
 * @Description:
 */

public class KMP {

    private String pat;

    // state machine
    private int[][] dfa;


    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        int R = 256;

        // state machine represent back to after matching
        dfa = new int[R][M];

        // initial self-compare always make j + 1
        dfa[pat.charAt(0)][0] = 1;

        // compute dfa[][j]
        for (int X = 0, j = 1; j < M; ++j) {
            for (int c = 0; c < R; ++c) {
                // the value after matching false
                dfa[c][j] = dfa[c][X];
            }
            // set successfully matching value
            dfa[pat.charAt(j)][j] = j + 1;
            // update state machine
            X = dfa[pat.charAt(j)][X];
        }
    }

    public int search(String txt) {
        int i, j, N = txt.length(), M = pat.length();
        for (i = 0, j = 0; i < N && j < M; ++i) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == M) return i - M;
        else return N;
    }

    public static void main(String[] args) {
    }
}
