package symbol_table;

import java.util.Iterator;

/**
 * @Auther:
 * @Date: 2022/4/1 14:05
 * @Description:
 */

public class ST<Key, Value> implements STAPI<Key, Value>{

    public ST() {}

    /**
     * add or change val of key
     * @param key key needed to be added or changed
     * @param val update key
     */
    public void put (Key key, Value val) {}

    /**
     * get val of key
     * @param key key
     * @return val of key
     */
    public Value get (Key key) {return null;}

    /**
     * delete key in ST
     * @param key key needed to be deleted
     */
    public void delete (Key key) {put(key, null);}

    /**
     * check whether is a key in ST
     * @param key key
     * @return boolean
     */
    public boolean contains (Key key) {return get(key) != null;}

    /**
     * ST is empty ?
     * @return boolean
     */
    public boolean isEmpty () {return size() == 0;}

    public int size () {return 0;}

    /**
     * return an iterator to check all Key
     * @return iterator
     */
    public Iterable<Key> keys () {return null;}

    public static void main(String[] args) {
    }
}
