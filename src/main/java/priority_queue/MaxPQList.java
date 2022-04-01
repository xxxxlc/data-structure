package priority_queue;

import func.Print;
import sort.InsertSort;

import java.util.LinkedList;

/**
 * @Auther:
 * @Date: 2022/3/21 10:49
 * @Description: List implements priority queue
 */


public class MaxPQList<Key extends Comparable<Key>> {
    public LinkedList<Comparable> list;
    public MaxPQList() {
        list = new LinkedList<>();
    }

    public MaxPQList(int max) {
        for (int i = 0; i < max; ++i){
            list.add(0);
        }
    }

    public MaxPQList(Key[] a) {
        InsertSort insert_sort = new InsertSort();
        insert_sort.sort(a);

        list = new LinkedList<>();

        for (int i = 0; i < a.length; ++i) {
            list.add(a[i]);
        }
    }

    public void insert(Key v) {
        /**
         * put a element into priority queues
         */
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).compareTo(v) < 0) {
                list.add(i, v);
                break;
            }
        }
    }

    public Key max() {
        /**
         * return max element of priority queues
         */
        return (Key) list.get(0);
    }

    public Key delMax() {
        /**
         * delete and return max element of priority queues
         */
        return (Key) list.removeFirst();
    }

    public boolean isEmpty() {
        /**
         * return whether the queue is empty
         */
        return list.size() == 0;
    }

    public int size() {
        /**
         * return number of element in priority queues
         */
        return list.size();
    }

    public void print_pq() {
        for (int i = 0; i < list.size(); ++i) {
            Print.printwt(list.get(i));
        }
        Print.print();
    }

    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 6, 8, 9, 7};
        MaxPQList pq = new MaxPQList(array);
        pq.insert(5);
        pq.print_pq();
        Print.print(pq.max());
        Print.print(pq.delMax());
        pq.print_pq();
    }
}
