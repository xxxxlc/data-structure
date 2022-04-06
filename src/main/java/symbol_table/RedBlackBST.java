package symbol_table;

import java.security.Key;
import java.util.NoSuchElementException;

/**
 * @Auther:
 * @Date: 2022/4/4 11:12
 * @Description: not understand
 */

public class RedBlackBST<Key extends Comparable<Key>, Value> implements STAPI<Key, Value>{

    // define if its link is red, variable is true. otherwise false
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        Key key;
        Value val;
        Node right, left;
        // number of node of this tree base on this node
        int N;
        // the color of link which its father node link to it
        boolean color;

        Node (Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    /**
     * determine whether link is red
     * @param x node
     * @return boolean
     */
    private boolean isRed (Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private int size (Node x) {
        if (x == null) return 0;
        return x.N;
    }

    /**
     * rotate RED link right to left
     * @param h node
     * @return node
     */
    private Node rotateLeft (Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * rotate RED link left to right
     * @param h node
     * @return node
     */
    private Node rotateRight (Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.right) + size(h.left);
        return x;
    }

    /**
     * turn h link to RED, and turn its sun node to black
     * four node to two tree
     * @param h node
     */
    private void flipColors (Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    /*********************************************************
     * RedBlack especial implement
     *********************************************************/

    /**
     * get value of key
     * @param h node
     * @param key key
     * @return value
     */
    private Value get (Node h, Key key) {
        if (h == null) return null;
        int cmp = key.compareTo(h.key);
        if (cmp > 0) return get(h.right, key);
        else if (cmp < 0) return get(h.right, key);
        else return h.val;
    }

    /**
     * construct RED BLACK tree
     * insert operation
     * @param h node
     * @param key key
     * @param val val
     * @return h
     */
    private Node put (Node h, Key key, Value val) {
        // insert, link to father node with RED link
        // insert operation always is RED link
        if (h == null) {
            return new Node(key, val, 1, RED);
        }

        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        // put hit right node of node h, take rotate left
        if (isRed(h.right) && !(isRed(h.left))) h = rotateLeft(h);

        // put hit left node of node h, and this a four key node situation, need to rotate left
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);

        // this node has two RED link, need to up node
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = 1 + size(h.right) + size(h.left);
        return h;
    }

    /**
     * delete min key in RedBlack tree
     * @param h ndoe
     * @return node
     */
    private Node deleteMin(Node h) {
        // there is no node less than node h, delete node h
        // because of perfect black balance of RedBlack tree, no think of right node of node h
        // right node must be rotated to left
        if (h.left == null) return null;

        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }

    /**
     * delete max key in RedBlack tree
     * @param h node
     * @return node
     */
    private Node deleteMax(Node h) {
        if (isRed(h.left)) {
            h = rotateRight(h);
        }
        if (h.right == null) {
            return null;
        }

        if (!isRed(h.right) && !isRed(h.right.left)){
            h = moveRedRight(h);
        }

        h.right = deleteMax(h.right);
        return balance(h);
    }

    /**
     * delete any node in RedBlack tree
     * @param h node
     * @return node
     */
    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                h.val = get(h.right, min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else
                h.right = delete(h.right, key);
        }
        return balance(h);
    }

    /**
     * turn h.left or h.left.left to RED
     * assume h.left and h.left is BLACK
     * @param h node
     * @return node
     */
    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }

    /**
     * turn h.right or h.right.left to RED
     * assume h.right and h.right.left is BLACK
     * @param h node
     * @return node
     */
    private Node moveRedRight(Node h) {
        flipColors(h);
        if (!isRed(h.left.left)) {
            h = rotateRight(h);
        }
        return h;
    }

    /**
     * sort node which are chaos by delete
     * @param h node
     * @return node
     */
    private Node balance(Node h){
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }
        if (isRed(h.right) && !(isRed(h.left))) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.N = 1 + size(h.right) + size(h.left);
        return h;
    }


    /*********************************************************
     * BST especial implement
     *********************************************************/

    /**
     * return min key node in tree
     * @param h node
     * @return node
     */
    private Node min(Node h) {
        if (h.left == null) return h;
        return min(h.left);
    }

    /**
     * return max key node in tree
     * @param h node
     * @return node
     */
    private Node max(Node h) {
        if (h.right == null) return h;
        return max(h.right);
    }

    private Node select(Node h, int k) {
        if (h == null) return null;
        int t = size(h.left);
        if (t > k) return select(h.left, k);
        else if (t < k) return select(h.right, k - t - 1);
        else return h;
    }

    private Node floor (Node h, Key key) {
        if (h == null) return null;
        int cmp = key.compareTo(h.key);
        if (cmp == 0) return h;
        if (cmp < 0) return floor(h.left, key);
        Node t = floor(h.right, key);
        if (t != null) return t;
        else return h;
    }

    private Node ceiling (Node h, Key key) {
        if (h == null) return null;
        int cmp = key.compareTo(h.key);
        if (cmp == 0) return h;
        if (cmp > 0) return ceiling(h.right, key);
        Node t = ceiling(h.left, key);
        if (t != null) return t;
        else return h;
    }


    /***************************************************************************
     * implement function.
     ***************************************************************************/
    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    @Override
    public void delete(Key key) {
        if (!contains(key)) throw new NoSuchElementException("no such key");
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    @Override
    public boolean contains(Key key) {
        return get(key) == null;
    }

    @Override
    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    @Override
    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public Key min () {
        return min(root).key;
    }

    @Override
    public Key max() {
        return max(root).key;
    }

    @Override
    public Key select (int k) {
        return select(root, k).key;
    }

    @Override
    public Key floor (Key key) {
        Node h = floor(root, key);
        if (h == null) return null;
        return h.key;
    }

    @Override
    public Key ceiling (Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    @Override
    public Iterable keys() {
        return null;
    }

    public static void main(String[] args) {
        RedBlackBST<Character, Integer> rbbst = new RedBlackBST<>();
        for (int i = 0; i < 10; ++i) {
            rbbst.put((char) (i + 97), i);
        }
    }
}
