package Graph;

import search.UF;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Auther:
 * @Date: 2022/4/16 15:32
 * @Description:
 */

public class KruskalMST {

    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new ArrayDeque<Edge>();
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();

        for (Edge e : G.edges()) {
            pq.add(e);
        }

        UF uf = new UF(G.V());

        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.poll();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.offer(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public static void main(String[] args) {
    }
}
