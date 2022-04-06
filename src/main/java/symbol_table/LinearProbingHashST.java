package symbol_table;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import static func.Print.printwt;

/**
 * @Auther:
 * @Date: 2022/4/6 14:28
 * @Description:
 */

public class LinearProbingHashST<Key, Value> {

    // total number of key and value
    private int N;

    //the size of linear probing hash set
    private int M = 16;

    // keys
    private Key[] keys;

    // value
    private Value[] vals;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public LinearProbingHashST(int cap) {
        M = cap;
        keys = (Key[]) new Object[cap];
        vals = (Value[]) new Object[cap];
    }


    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<>(cap);

        for (int i = 0; i < M; ++i) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }

        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    /**
     * put key in st
     * @param key key
     * @param val val
     */
    public void put(Key key, Value val) {
        if (N >= M / 2) resize(2 * M);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }

        // not find key in st, add a new key and value in null
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    /**
     * get value of key
     * @param key key
     * @return val
     */
    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i] == key) {
                return vals[i];
            }
        }
        return null;
    }


    public boolean contains(Key key) {
        return get(key) == null;
    }

    /**
     * delete key in st
     * @param key key
     */
    public void delete(Key key) {
        if (!contains(key)) throw new NoSuchElementException("no such key");

        // find this key, put i to null
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }
        keys[i] = null;
        vals[i] = null;

        // delete keys after this key, put them again
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }
        N--;

        if (N > 0 && N == M / 8) resize(M / 2);
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new LinkedList<>();

        for (int i = 0; i < M; ++i) {
            if (keys[i] != null) {
                q.add(keys[i]);
            }
        }

        return q;
    }

    public static void main(String[] args) {
        LinearProbingHashST<Character, Integer> lp = new LinearProbingHashST<>();
        for (int i = 0; i < 10; ++i) {
            lp.put((char) (i + 97), i);
        }

        for (Character k : lp.keys()) {
            printwt(k);
        }
    }
}
