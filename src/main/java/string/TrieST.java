package string;


import java.util.ArrayDeque;
import java.util.Queue;

import static func.Print.print;

/**
 * @Auther:
 * @Date: 2022/4/28 16:01
 * @Description:
 */

public class TrieST<Value> extends StringST<Value>{

    protected static int R = 256;
    protected Node root;

    protected static class Node {
        // because java don't support <T> array
        // so val must be Object
        protected Object val;
        protected Node[] next = new Node[R];
    }

    /**
     * compute number of trieST
     * @param x Node
     * @return int
     */
    protected int size(Node x) {
        if (x == null) return 0;
        int cnt = 0;

        if (x.val != null) cnt++;
        for (char c = 0; c < R; ++c) {
            cnt += size(x.next[c]);
        }

        return cnt;
    }

    /**
     * collect all ket in TrieST
     * @param x node
     * @param pre string
     * @param q queue
     */
    protected void collect(Node x, String pre, Queue<String> q) {
        if (x == null) return;
        if (x.val != null) q.offer(pre);

        for (char c = 0; c < R; ++c) {
            collect(x.next[c], pre + c, q);
        }
    }


    /**
     * collect all key match with pat
     * @param x node
     * @param pre before string
     * @param pat match string
     * @param q result queue
     */
    protected void collect(Node x, String pre, String pat, Queue<String> q) {
        int d = pre.length();
        if (x == null) return;
        if (d == pat.length() && x.val != null) q.offer(pre);
        if (d == pat.length()) return;

        char next = pat.charAt(d);
        for (char c = 0; c < R; ++c) {
            if (next == '.' || next == c) {
                collect(x.next[c], pre + c, pat, q);
            }
        }
    }

    /**
     * check longest key string match with s
     * @param x node
     * @param s match string
     * @param d rank d char
     * @param length length
     * @return
     */
    protected int search(Node x, String s, int d, int length) {
        if (x == null) return length;
        if (x.val != null) length = d;
        if (d == s.length()) return length;

        char c = s.charAt(d);
        return search(x.next[c], s, d + 1, length);
    }

    /**
     * return value connect with key based on node x
     * @param x node
     * @param key key
     * @param d rank d char
     * @return node
     */
    protected Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        // find rank d it matches trieST
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    /**
     * return after update trieST based on node x
     * @param x node
     * @param key key
     * @param val val
     * @param d rank d char
     * @return node
     */
    protected Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }

        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    /**
     * delete a key in TrieST based on node x
     * @param x node
     * @param key key
     * @param d rank d char
     * @return node
     */
    protected Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) x.val = null;
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }

        if (x.val != null) return x;

        for (char c = 0; c < R; ++c) {
            if (x.next[c] != null) return x;
        }

        return null;
    }



    @Override
    public int size() {
        return size(root);
    }

    @Override
    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }

    @Override
    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    @Override
    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new ArrayDeque<String>();
        collect(get(root, pre, 0), pre, q);
        return q;
    }

    @Override
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    @Override
    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new ArrayDeque<String>();
        collect(root, "", pat, q);
        return q;
    }

    @Override
    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    @Override
    public void delete(String key) {
        root = delete(root, key, 0);
    }


    public static void main(String[] args) {
        TrieST<Integer> st = new TrieST<>();
        st.put("she", 0);
        st.put("shells", 1);
        st.put("sea", 2);

        print(st.get(""));

        for (String c : st.keys()) {
            print(c);
        }

        for (String c : st.keysWithPrefix("se")) {
            print(c);
        }

        for (String c : st.keysThatMatch("s..")) {
            print(c);
        }
    }
}
