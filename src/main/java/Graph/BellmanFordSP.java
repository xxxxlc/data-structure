package Graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Auther:
 * @Date: 2022/4/23 16:02
 * @Description: this method used to solve the shortest path in graph which has negative edge
 */

public class BellmanFordSP extends SP{


    // this node is in queue?
    private boolean[] onQ;

    // relaxing node
    private Queue<Integer> queue;

    // time of using relax
    private int cost;

    // there is a negative cycle in edgeTo
    private Iterable<Integer> cycle;

    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        super(G, s);

        onQ = new boolean[G.V()];
        queue = new ArrayDeque<>();

        queue.offer(s);
        onQ[s] = true;

        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.poll();
            onQ[v] = false;
            relax(G, v);
        }
    }

    @Override
    protected void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adjEdge(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;

                if (!onQ[w]) {
                    queue.offer(w);
                    onQ[w] = true;
                }
            }
            if (cost++ % G.V() == 0) {
                findNegativeCycle();
            }
        }
    }

    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt;
        spt = new EdgeWeightedDigraph(V);
        for (int  v = 0; v < V; ++v) {
            if (edgeTo[v] != null) {
                spt.addEdge(edgeTo[v]);
            }
        }

        DirectedCycle<EdgeWeightedDigraph> cf = new DirectedCycle<EdgeWeightedDigraph>(spt);

        cycle = cf.cycle();
    }

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    public Iterable<Integer> nagetiveCycle() {
        return cycle;
    }

    public static void main(String[] args) {
    }
}
