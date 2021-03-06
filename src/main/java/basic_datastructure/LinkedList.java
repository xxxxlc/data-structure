package basic_datastructure;

import java.util.Iterator;

import static func.Print.*;

/**
 * @Auther:
 * @Date: 2022/3/30 20:02
 * @Description:
 */

public class LinkedList<Item> implements Iterable<Item> {

    // define class of Node
    public class Node {
        public Item item;
        public Node next;
    }

    // head of LinkedList
    public Node head;

    // tail of LinkedList
    public Node tail;

    // length of LinkedList
    public int N = 0;

    public LinkedList() {
        head = new Node();
        tail = head;
    }

    public LinkedList(Item[] a) {
        head = new Node();
        tail = head;
        for (Item item : a) {
            addTail(item);
        }
    }

    /**
     * add element to tail of LinkedList
     * @param item element
     */
    public void addTail (Item item) {
        if (tail.item == null) {
            tail.item = item;
            return;
        }
        Node newNode = new Node();
        newNode.item = item;
        tail.next = newNode;
        tail = tail.next;
        N++;
    }


    /**
     * add element to head of LinkedList
     * @param item element
     */
    public void addHead (Item item) {
        if (head.item == null) {
            head.item = item;
            return;
        }
        Node newNode = head;
        head = new Node();
        head.item = item;
        head.next = newNode;
        N++;
    }

    /**
     * delete head of LinkedList
     * @return delete element
     */
    public Item popHead () {
        Item temp = head.item;
        head = head.next;
        N--;
        return temp;
    }

    /**
     * delete element of tail of LinkedList
     * @return delete element
     */
    public Item popTail () {
        return popIndex(N);
    }

    /**
     * delete element of index of idx
     * @param idx the index of element needed to be deleted
     * @return element which is deleted
     */
    public Item popIndex (int idx) {
        int i = 0;
        Node cur = head;
        Node pre = new Node();

        // find element of idx
        while (i != idx) {
            i++;
            pre = cur;
            cur = cur.next;
        }

        // delete this node by transition value
        Item temp = cur.item;
        while (cur != tail) {
            cur.item = cur.next.item;
            pre = cur;
            cur = cur.next;
        }

        // delete tail
        pre.next = null;
        N--;
        tail = pre;

        return temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    public class ListIterator implements Iterator<Item> {
        public Node i = head;
        @Override
        public boolean hasNext() {
            return i != null;
        }

        @Override
        public Item next() {
            Node pre = i;
            i = i.next;
            return pre.item;
        }
    }

    // Print List
    public void printList (Node head) {
        while (head != null) {
            printwt(head.item);
            head = head.next;
        }
        print();
    }

    public static void main(String[] args) {
        Integer[] s1 = {4, 8, 6, 5, 9, 10, 5};
        LinkedList<Integer> linkedList1 = new LinkedList<>(s1);
        linkedList1.printList(linkedList1.head);
        linkedList1.addTail(15);
        linkedList1.addHead(12);
        linkedList1.printList(linkedList1.head);
        print(linkedList1.popHead());
        print(linkedList1.popTail());
        print(linkedList1.popIndex(3));
        linkedList1.printList(linkedList1.head);

        for (Object a : linkedList1) {
            printwt(a);
        }
    }
}
