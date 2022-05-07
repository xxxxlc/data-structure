package string;


import symbol_table.STAPI;

/**
 * @Auther:
 * @Date: 2022/4/28 15:34
 * @Description:
 */

public class StringST<Value> implements STAPI<String, Value> {

    /**
     * create a StringST
     */
    public StringST() {

    }

    @Override
    public void put(String key, Value val) {

    }

    @Override
    public Value get(String key) {
        return null;
    }

    @Override
    public void delete(String key) {

    }

    @Override
    public boolean contains(String key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<String> keys() {
        return null;
    }



    /**
     * return the longest key in prefix s
     * @param s prefix
     * @return longest key
     */
    public String longestPrefixOf(String s) {
        return null;
    }

    /**
     * return all start with prefix s
     * @param s prefix
     * @return Iterator
     */
    public Iterable<String> keysWithPrefix(String s) {
        return null;
    }

    /**
     * return all match with prefix s
     * @param s prefix
     * @return Iterator
     */
    public Iterable<String> keysThatMatch(String s) {
        return null;
    }



    public static void main(String[] args) {
    }
}
