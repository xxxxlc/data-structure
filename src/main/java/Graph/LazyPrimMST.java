package Graph;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Auther:
 * @Date: 2022/4/16 14:23
 * @Description:
 */

public class LazyPrimMST {

    // nodes of minimal spanning tree
    private boolean[] marked;
    // edge of minimal spanning tree
    private Queue<Edge> mst;
    // cross cut edge
    private PriorityQueue<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new PriorityQueue<Edge>();
        marked = new boolean[G.V()];
        mst = new ArrayDeque<Edge>();

        visit(G, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.offer(e);
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    /**
     * mark node v and put all nodes that connected v and not marked to pq
     * @param G
     * @param v
     */
    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adjEdge(v)) {
            if (!marked[e.other(v)]) pq.add(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }


    public static void main(String[] args) {
    }
}
