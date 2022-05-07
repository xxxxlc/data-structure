package string;

import com.sun.jdi.Value;

import java.util.ArrayDeque;
import java.util.Queue;

import static func.Print.print;

/**
 * @Auther:
 * @Date: 2022/4/28 16:53
 * @Description:
 */

public class TST<Value> extends StringST<Value>{

    protected Node root;

    protected class Node {
        char c;
        Node left, mid, right;
        Value val;
    }


    protected Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (key.length() == 0) return x;
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid, key, d + 1);
        else return x;
    }

    protected Node put(Node x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c) x.left = put(x.left, key, val, d);
        else if (c > x.c) x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1) x.mid = put(x.mid, key, val, d + 1);
        else x.val = val;
        return x;
    }

    protected int size(Node x) {
        if (x == null) return 0;
        int cnt = 0;

        if (x.val != null) cnt++;
        cnt += size(x.left);
        cnt += size(x.mid);
        cnt += size(x.right);

        return cnt;
    }

    protected void collect(Node x, String pre, Queue<String> q) {
        if (x == null) return;
        if (x.val != null) q.offer(pre + x.c);

        collect(x.left, pre, q);
        collect(x.right, pre, q);
        collect(x.mid, pre + x.c, q);
    }

    protected void collect(Node x, String pre, String pat, Queue<String> q) {
        int d = pre.length();
        if (x == null) return;
        if (d == pat.length()) return;

        char next = pat.charAt(d);

        if (next == '.') {
            if (d == pat.length() - 1 && x.val != null) q.offer(pre + x.c);
            collect(x.mid, pre + x.c, pat, q);
            collect(x.left, pre, pat, q);
            collect(x.right, pre, pat, q);
        }
        else {

            if (x.c == next) {
                if (d == pat.length() - 1 && x.val != null) q.offer(pre + x.c);
                collect(x.mid, pre + x.c, pat, q);
            } else if (next < x.c) collect(x.left, pre, pat, q);
            else collect(x.right, pre, pat, q);
        }

    }

    protected Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    protected Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) x.val = null;
        else {
            char c = key.charAt(d);
            if (c == x.c) x.mid = delete(x.mid, key, d + 1);
            else if (c < x.c) x.left = delete(x.left, key, d);
            else x.right = delete(x.right, key, d);
        }

        if (x.val == null) return x;

        if (x.left == null && x.right == null && x.mid == null) {
            return null;
        }

        return x;

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
    public int size() {
        return size(root);
    }

    @Override
    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new ArrayDeque<>();
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
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    public static void main(String[] args) {
        TST<Integer> st = new TST<>();
        st.put("she", 0);
        st.put("shells", 1);
        st.put("sea", 2);
        st.put("stable", 3);

        print(st.size());

        print(st.get(""));

        for (String c : st.keys()) {
            print(c);
        }

        for (String c : st.keysThatMatch("s..")) {
            print(c);
        }


    }
}
