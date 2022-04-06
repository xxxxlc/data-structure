package symbol_table;

/**
 * @Auther:
 * @Date: 2022/4/2 11:08
 * @Description:
 */

public interface STAPI<Key, Value> {

    /**
     * add or change val of key
     * @param key key needed to be added or changed
     * @param val update key
     */
    public void put (Key key, Value val);

    /**
     * get val of key
     * @param key key
     * @return val of key
     */
    public Value get (Key key);

    /**
     * delete key in ST
     * @param key key needed to be deleted
     */
    public void delete (Key key);

    /**
     * check whether is a key in ST
     * @param key key
     * @return boolean
     */
    public boolean contains (Key key);

    /**
     * ST is empty ?
     * @return boolean
     */
    public boolean isEmpty ();

    public int size ();

    /**
     * return minimum key
     * @return Key
     */
    default public Key min () {
        return null;
    }

    /**
     * return maximum key
     * @return Key
     */
    default public Key max () {
        return null;
    }

    /**
     * return maximum key which less than key
     * @param key key
     * @return key
     */
    default public Key floor (Key key) {
        return null;
    }

    /**
     * return minimum key which more than key
     * @param key key
     * @return key
     */
    default public Key ceiling (Key key) {
        return null;
    }

    /**
     * number of keys which less than key
     * @param key key
     * @return number of keys int
     */
    default public int rank (Key key) {return 0;}

    /**
     * return rank k keys
     * @param k rank k
     * @return key
     */
    default public Key select (int k) {return null;}

    /**
     * delete minimum key
     */
    default public void deleteMin () {}

    /**
     * delete maximum key
     */
    default public void deleteMax () {}

    /**
     * return an iterator to check all Key
     * @return iterator
     */
    public Iterable<Key> keys ();

}
