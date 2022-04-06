package symbol_table;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import static func.Print.printwt;

/**
 * @Auther:
 * @Date: 2022/4/1 14:20
 * @Description: St implemented by disordered List
 */

public class SequentialSearchSt<Key, Value> extends ST<Key, Value> {
    public class Node {
        Key key;
        Value val;
        Node next;

        public Node (Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Node head;
    public int N = 0;

    @Override
    public Value get (Key key) {
        if (isEmpty()) return null;
        for (Node x = head; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    @Override
    public void put (Key key, Value val) {
        if (val == null) {
            delete(key);
            return ;
        }

        for (Node x = head; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        head = new Node(key, val, head);
        N++;
    }

    @Override
    public int size () {
        return N;
    }

    @Override
    public void delete(Key key) {
        if (!contains(key)) {
            throw new NoSuchElementException("no this key in ST");
        }

        Node pre = head;
        for (Node x = head; x != null; x = x.next) {
            if (key.equals(x.key)) {
                pre.next = x.next;
            }
            pre = x;
        }
    }

    @Override
    public Iterable<Key> keys () {
        Queue<Key> q = new LinkedList<>();
        for (Node x = head; x != null; x = x.next) {
            q.add(x.key);
        }
        return q;
    }

    public static void main(String[] args) {
        SequentialSearchSt<String, Integer> st = new SequentialSearchSt<>();
        st.put("a", 1);
        st.put("b", 2);
        st.put("c", 3);
        st.put("d", 4);
        st.delete("c");

        for (String c : st.keys()) {
            printwt(st.get(c));
        }
    }
}
