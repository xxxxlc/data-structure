package Graph;

import static func.Print.*;

/**
 * @Auther:
 * @Date: 2022/4/21 15:44
 * @Description:
 */

public class AcyclicSP extends SP{

    public AcyclicSP(EdgeWeightedDigraph G, int s) {
        super(G, s);

        Topological<EdgeWeightedDigraph> top = new Topological<EdgeWeightedDigraph>(G);

        for (int v : top.order()) {
            relax(G, v);
        }
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(8);
        int[][] edge = {
                {5, 4}, {4, 7}, {5, 7}, {5, 1}, {4, 0}, {0, 2},
                {3, 7}, {1, 3}, {7, 2}, {6, 2}, {3, 6}, {6, 0}, {6, 4},
        };
        double[] weight = {
                0.35, 0.37, 0.28, 0.32, 0.38, 0.26,
                0.39, 0.29, 0.34, 0.40, 0.52, 0.58, 0.93,
        };

        for (int e = 0; e < edge.length; ++e) {
            ewd.addEdge(new DirectedEdge(edge[e][0], edge[e][1], weight[e]));
        }
        int s = 5;

        AcyclicSP sp = new AcyclicSP(ewd, s);

        for (int t = 0; t < ewd.V(); ++t) {
            printwt(s + " to " + t);
            printf(" (%4.2f)", sp.distTo(t));
            print();
        }
    }
}
