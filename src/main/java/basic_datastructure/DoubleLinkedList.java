package basic_datastructure;

import java.util.Iterator;

import static func.Print.print;
import static func.Print.printList;

/**
 * @Auther:
 * @Date: 2022/3/31 16:20
 * @Description: DoubleLinkedList
 */

public class DoubleLinkedList<Item> implements Iterable<Item>{

    // define class of Node
    public class DoubleNode {
        public Item item;
        public DoubleNode next;
        public DoubleNode pre;
    }

    // length of List
    public int N = 0;
    // head of List
    public DoubleNode head;
    // tail of List
    public DoubleNode tail;

    public DoubleLinkedList () {
        head = new DoubleNode();
        tail = head;
    }

    public DoubleLinkedList (Item[] a) {
        head = new DoubleNode();
        tail = head;

        for (Item item : a) {
            addTail(item);
        }
    }

    /**
     * add element to tail of List
     * @param item element
     */
    public void addTail (Item item) {
        if (tail.item == null) {
            tail.item = item;
            return;
        }

        DoubleNode node = new DoubleNode();
        node.item = item;
        tail.next = node;
        node.pre = tail;
        tail = tail.next;
        N++;
    }

    /**
     * add element to head of List
     * @param item element
     */
    public void addHead (Item item) {
        if (head.item == null) {
            head.item = item;
            return;
        }

        DoubleNode newNode = head;
        head = new DoubleNode();
        head.item = item;
        newNode.pre = head;
        head.next = newNode;
        N++;
    }

    /**
     * delete head of List
     * @return delete element
     */
    public Item popHead () {
        Item temp = head.item;
        head = head.next;
        N--;
        return temp;
    }

    /**
     * delete element of tail of List
     * @return delete element
     */
    public Item popTail () {
        Item temp = tail.item;
        tail = tail.pre;
        tail.next = null;
        return temp;
    }

    /**
     * delete element of index of idx
     * @param idx the index of element needed to be deleted
     * @return element which is deleted
     */
    public Item popIndex (int idx) {
        int i = 0;
        DoubleNode cur = head;
        // find element of idx
        while (i != idx) {
            i++;
            cur = cur.next;
        }

        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;
        return cur.item;
    }


    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    public static void main(String[] args) {
        Integer[] s1 = {4, 8, 6, 5, 9, 10, 5};
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<>(s1);
        printList(list1.head);
        list1.addTail(25);
        list1.addHead(52);
        printList(list1.head);
        print(list1.popHead());
        print(list1.popTail());
        print(list1.popIndex(3));
        printList(list1.head);
    }
}
