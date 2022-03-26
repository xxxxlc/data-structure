package priority_queue;

/**
 * @Auther:
 * @Date: 2022/3/20 15:32
 * @Description: API for generic priority queues
 */

public class MaxPQ<Key extends Comparable<Key>> {

    MaxPQ() {
        /**
         * construct a priority queues
          */
    }

    MaxPQ(int max) {
        /**
         * construct a priority queues with length of max
         */

    }

    public void insert(Key v) {
        /**
         * put a element into priority queues
         */
    }

    public Key max() {
        /**
         * return max element of priority queues
         */
        return null;
    }

    public Key delMax() {
        /**
         * delete and return max element of priority queues
         */
        return null;
    }

    public boolean isEmpty() {
        /**
         * return whether the queue is empty
         */
        return false;
    }

    public int size() {
        /**
         * return number of element in priority queues
         */
        return 0;
    }

    public static void main(String[] args) {
    }
}
