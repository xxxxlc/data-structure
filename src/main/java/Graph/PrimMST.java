package Graph;

import priority_queue.IndexMinPQ;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther:
 * @Date: 2022/4/16 14:55
 * @Description:
 */

public class PrimMST {

    // the edge closest to the tree
    private Edge[] edgeTo;

    // disTo[w] = edgeTo[w].weight()
    private double[] distTo;

    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];

        for (int v = 0; v < G.V(); ++v) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        pq = new IndexMinPQ<Double>(G.V());

        distTo[0] = 0.0;
        pq.insert(0, 0.0);

        while (!pq.isEmpty()) {
            visit(G, pq.delMin());
        }

    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adjEdge(v)) {
            int w = e.other(v);

            if (marked[w]) continue;
            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges() {
        Set<Edge> mst = new HashSet<Edge>();
        for (int v = 1; v < edgeTo.length; ++v) {
            mst.add(edgeTo[v]);
        }
        return mst;
    }

    public static void main(String[] args) {
    }
}
