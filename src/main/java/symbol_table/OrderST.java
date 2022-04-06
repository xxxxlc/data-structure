package symbol_table;

/**
 * @Auther:
 * @Date: 2022/4/1 15:16
 * @Description: ST implemented by O
 */

public class OrderST<Key extends Comparable<Key>, Value> extends ST<Key, Value>{
    /**
     * return minimum key
     * @return Key
     */
    public Key min () {return null;}

    /**
     * return maximum key
     * @return Key
     */
    public Key max () {return null;}

    /**
     * return maximum key which less than key
     * @param key key
     * @return key
     */
    public Key floor (Key key) {return null;}

    /**
     * return minimum key which more than key
     * @param key key
     * @return key
     */
    public Key ceiling (Key key) {return null;}

    /**
     * number of keys which less than key
     * @param key key
     * @return number of keys int
     */
    public int rank (Key key) {return 0;}

    /**
     * return rank k keys
     * @param k rank k
     * @return key
     */
    public Key select (int k) {return null;}

    /**
     * delete minimum key
     */
    public void deleteMin () {delete(min());}

    /**
     * delete maximum key
     */
    public void deleteMax () {delete(max());}

    /**
     * return keys between left and right
     * @param left key in left
     * @param right ley in right
     * @return iterator
     */
    public Iterable<Key> keys (Key left, Key right) {return null;}

    @Override
    public Iterable<Key> keys () {return keys(min(), max());}

    /**
     * number of keys between left and right
     * @param left key
     * @param right key
     * @return number of keys
     */
    public int size (Key left, Key right) {
        if (left.compareTo(right) >= 0) return 0;
        else if (contains(right)) return rank(right) - rank(left) + 1;
        else return rank(right) - rank(left);
    }

    public static void main(String[] args) {
    }
}
