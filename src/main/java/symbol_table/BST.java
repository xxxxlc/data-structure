package symbol_table;

import basic_datastructure.BinaryTree;
import basic_datastructure.Queue;

import static func.Print.*;

/**
 * @Auther:
 * @Date: 2022/4/2 10:54
 * @Description: BST
 */

public class BST<Key extends Comparable<Key>, Value> extends BinaryTree<Value> implements STAPI<Key, Value>{

    // root of binary tree
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }


    private int size (Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     * base on root of Node x, find value of key
     * @param x Node x
     * @param key key
     * @return value
     */
    private Value get (Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) return get(x.right, key);
        else if (cmp < 0) return get(x.left, key);
        else return x.val;
    }

    /**
     * if key exist in binary tree base on Node x, update its value.
     * Otherwise take key and val as a new key insert tree
     * @param x Node x
     * @param key key
     * @param val val
     * @return Node x after update
     */
    private Node put (Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * return minimum node in binary tree based on node x
     * @param x node x
     * @return minimum node
     */
    private Node min (Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    /**
     * return maximum node in binary tree based on node x
     * @param x node x
     * @return maximum node
     */
    private Node max (Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    /**
     * return less than key but max or equal to key Node x' in tree based Node x
     * @param x node x
     * @param key key
     * @return node x
     */
    private Node floor (Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    /**
     * return more than key but minimum or equal to key Node x' based on Node x
     * @param x node x
     * @param key key
     * @return node x
     */
    private Node ceiling (Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    /**
     * return node of rank k based on node x
     * @param x node x
     * @param k rank k
     * @return node
     */
    private Node select (Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    /**
     * delete minimum node in binary tree based on node x
     * @param x node
     * @return node
     */
    private Node deleteMin (Node x) {
        // if there has no Node less than Node x, delete Node x (return its right node)
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.right) + size(x.left) + 1;
        return x;
    }

    /**
     * delete maximum node in binary tree based on node x
     * @param x node
     * @return node
     */
    private Node deleteMax (Node x) {
        // if there has no Node more than Node x, delete Node x (return its left node)
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.right) + size(x.left) + 1;
        return x;
    }


    private Node delete (Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            // exchange x and x + 1, delete x + 1, update x.right and x.left
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.right) + size(x.left) + 1;
        return x;
    }

    private void printBinaryTree(Node x) {
        if (x == null) return;
        printBinaryTree(x.left);
        printwt(x.key);
        printBinaryTree(x.right);
    }

    private void keys(Node x, Queue<Key> queue, Key left, Key right) {
        if (x == null) return;
        int cmpLeft = left.compareTo(x.key);
        int cmpRight = right.compareTo(x.key);
        if (cmpLeft < 0) keys(x.left, queue, left, right);
        if (cmpLeft <= 0 && cmpRight >= 0) queue.enqueue(x.key);
        if (cmpRight > 0) keys(x.right, queue, left, right);
    }

    public Iterable<Key> keys (Key left, Key right) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, left, right);
        return queue;
    }


    /***************************************************************************
     * implement function.
     ***************************************************************************/
    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    @Override
    public Value get (Key key) {
        return get(root, key);
    }

    @Override
    public boolean contains(Key key) {
        return get(key) == null;
    }

    @Override
    public boolean isEmpty() {
        return size(root) == 0;
    }

    @Override
    public int size () {
        return size(root);
    }

    @Override
    public Key min () {
        return min(root).key;
    }

    @Override
    public Key max () {
        return max(root).key;
    }

    @Override
    public Key floor (Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    @Override
    public Key ceiling (Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    @Override
    public Key select (int k) {
        return select(root, k).key;
    }

    @Override
    public void deleteMin () {
        root = deleteMin(root);
    }

    @Override
    public void deleteMax () {
        root = deleteMax(root);
    }

    @Override
    public void delete (Key key) {
        root = delete(root, key);
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }


    public static void main(String[] args) {
        BST<Character, Integer> bst = new BST<>();

        for (int i = 0; i < 10; ++i) {
            bst.put((char) (i + 97), i);
        }

        bst.printBinaryTree(bst.root);
        print();

//        bst.deleteMax();
//        bst.deleteMin();
//        bst.deleteMin();
//        bst.deleteMin();
        bst.delete('e');
        bst.printBinaryTree(bst.root);
        print();
        print(bst.get('c'));
    }
}
