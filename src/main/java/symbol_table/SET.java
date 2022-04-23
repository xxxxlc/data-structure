package symbol_table;

/**
 * @Auther:
 * @Date: 2022/4/6 18:06
 * @Description:
 */

public interface SET<Key> {
    /**
     * add the key into set
     * @param key key
     */
    public void add(Key key);

    /**
     * delete key in set
     * @param key key
     */
    public void delete(Key key);

    /**
     * whether contains this key
     * @param key key
     * @return boolean
     */
    public boolean contains(Key key);

    /**
     * set is empty?
     * @return boolean
     */
    public boolean isEmpty();

    /**
     * size
     * @return int
     */
    public int size();
}
