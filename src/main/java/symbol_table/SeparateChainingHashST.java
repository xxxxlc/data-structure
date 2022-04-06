package symbol_table;

import java.util.LinkedList;
import java.util.Queue;
import static func.Print.printwt;

/**
 * @Auther:
 * @Date: 2022/4/6 13:54
 * @Description: SeparateChainingHashST
 */

public class SeparateChainingHashST<Key, Value> {

    // total number of key and value
    private int N;

    // the size of hash st
    private int M;

    // the array that save list head
    private SequentialSearchSt<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchSt<Key, Value>[]) new SequentialSearchSt[M];

        for (int i = 0; i < M; ++i) {
            st[i] = new SequentialSearchSt<>();
        }
    }

    /**
     * the hash st function
     * key.hashCode() & 0x7fffffff: set 32 to 31 positive number
     * return index range [0, M - 1] randomly
     * @param key key
     * @return int index of array
     */
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /**
     * get value of key
     * @param key key
     * @return val
     */
    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    /**
     * put value of key
     * @param key key
     * @param val val
     */
    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);
        this.N++;
    }

    /**
     * delete value of key
     * @param key key
     */
    public void delete(Key key) {
        st[hash(key)].delete(key);
    }

    public  Iterable<Key> keys() {
        Queue<Key> q = new LinkedList<>();

        for (int i = 0; i < M; ++i) {
            for (Key k : st[i].keys()) {
                q.add(k);
            }
        }
        return q;
    }

    public static void main(String[] args) {
        SeparateChainingHashST<Character, Integer> sc  = new SeparateChainingHashST<>(10);
        for (int i = 0; i < 10; ++i) {
            sc.put((char) (i + 97), i);
        }

        for (Character k : sc.keys()) {
            printwt(k);
        }
    }
}
