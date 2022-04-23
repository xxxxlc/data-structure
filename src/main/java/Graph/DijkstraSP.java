package Graph;

import priority_queue.IndexMinPQ;

import static func.Print.*;

/**
 * @Auther:
 * @Date: 2022/4/20 14:01
 * @Description:
 */

public class DijkstraSP extends SP{

    protected IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        super(G, s);

        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
    }

    @Override
    protected void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adjEdge(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;

                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(8);
        int[][] edge = {
                {4, 5}, {5, 4}, {4, 7}, {5, 7}, {7, 5}, {5, 1}, {0, 4}, {0, 2},
                {7, 3}, {1, 3}, {2, 7}, {6, 2}, {3, 6}, {6, 0}, {6, 4},
        };
        double[] weight = {
                0.35, 0.35, 0.37, 0.28, 0.28, 0.32, 0.38, 0.26,
                0.39, 0.29, 0.34, 0.40, 0.52, 0.58, 0.93,
        };

        for (int e = 0; e < edge.length; ++e) {
            ewd.addEdge(new DirectedEdge(edge[e][0], edge[e][1], weight[e]));
        }
        int s = 0;
        DijkstraSP sp = new DijkstraSP(ewd, s);

        for (int t = 0; t < ewd.V(); ++t) {
            printwt(s + " to " + t);
            printf(" (%4.2f)", sp.distTo(t));
            print();
        }
    }
}
