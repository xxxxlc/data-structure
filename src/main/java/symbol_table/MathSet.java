package symbol_table;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * @Auther:
 * @Date: 2022/4/6 18:17
 * @Description:
 */

public class MathSet<Key> implements SET<Key> {

    private HashMap<Key,Boolean> st;
    private Key[] universe;
    private int N = 0;

    public MathSet(Key[] universe) {
        st = new HashMap<>();
        for (Key k : universe) {
            st.put(k, false);
        }
    }

    public MathSet<Key> complement() {
        MathSet<Key> t = new MathSet<Key>(st.keySet().toArray((Key[]) new Object[0]));
        for (Key k : st.keySet()) {
            if (!st.get(k)) t.add(k);
        }

        return t;
    }

    @Override
    public void add(Key key) {
        st.put(key, true);
        N++;
    }

    @Override
    public void delete(Key key) {
        if (!contains(key)) throw new NoSuchElementException("no such key");
        st.put(key, false);
    }

    @Override
    public boolean contains(Key key) {
        return st.get(key);
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    public static void main(String[] args) {
    }
}
