package symbol_table;

import basic_datastructure.Queue;

import java.util.NoSuchElementException;

import static func.Print.print;
import static func.Print.printwt;

/**
 * @Auther:
 * @Date: 2022/4/1 15:50
 * @Description:
 */

public class BinarySearchST<Key extends Comparable<Key>, Value> extends OrderST<Key, Value>{
    public Key[] keys;
    public Value[] vals;
    public int N;

    /**
     * resize length of ST to max
     * @param max new length
     */
    public void resize (int max) {
        Key[] tempKey = (Key[]) new Comparable[max];
        System.arraycopy(keys, 0, tempKey, 0, keys.length);
        keys = tempKey;

        Value[] tempVal = (Value[]) new Object[max];
        System.arraycopy(vals, 0, tempVal, 0, vals.length);
        vals = tempVal;
    }

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size () {return N;}

    public Value get (Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }

    public void put (Key key, Value val) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; --j) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;

        if (N == keys.length) resize(2 * keys.length);
    }

    public int rank (Key key) {
        int left = 0;
        int right = N - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp > 0) left = mid + 1;
            else if (cmp < 0) right = mid - 1;
            else return mid;
        }
        return left;
    }

    public Key min () {
        return keys[0];
    }

    public Key max () {
        return keys[N - 1];
    }

    public Key select (int k) {
        return keys[k];
    }

    public Key ceiling (Key key) {
        int i = rank(key);
        return keys[i];
    }

    public Key floor (Key key) {
        int i = rank(key);
        if (i == 0) return null;
        return keys[i - 1];
    }

    public void delete (Key key) {
        if (!contains(key)) throw new NoSuchElementException("no such key in ST");
        int i = rank(key);
        for (int j = i; j < N; ++j) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }
        keys[N] = null;
        vals[N--] = null;

        if (N > 0 && N < keys.length / 4) resize(keys.length / 2);
    }

    public Iterable<Key> keys (Key left, Key right) {
        Queue<Key> q = new Queue<>();

        for (int i = rank(left); i < rank(right); ++i) {
            q.enqueue(keys[i]);
        }
        if (contains(right)) q.enqueue(keys[rank(right)]);
        return q;
    }


    public static void main(String[] args) {
        BinarySearchST<String, Integer> bss = new BinarySearchST<>(10);
        bss.put("a", 1);
        bss.put("b", 2);
        bss.put("c", 3);
        bss.put("d", 4);
        bss.put("e", 5);

        bss.delete("c");
        print(bss.ceiling("c"));
        for (String k : bss.keys()) {
            printwt(bss.get(k));
        }
    }
}
