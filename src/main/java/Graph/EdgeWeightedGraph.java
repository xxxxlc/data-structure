package Graph;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @Auther:
 * @Date: 2022/4/16 14:06
 * @Description:
 */

public class EdgeWeightedGraph extends Graph{

    protected Set<Edge>[] adjEdge;

    public EdgeWeightedGraph(int V) {
        super(V);
        adjEdge = (Set<Edge>[]) new Set[V];

        for (int v = 0; v < V; ++v) {
            adjEdge[v] = new HashSet<Edge>();
        }
    }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adjEdge[v].add(e);
        adjEdge[w].add(e);
        adj[w].add(v);
        adj[v].add(w);
        E++;
    }

    public Iterable<Edge> adjEdge(int v) {
        return adjEdge[v];
    }

    public Iterable<Integer> adj(int v) {
        Queue<Integer> pq = new ArrayDeque<>();
        for (Edge e : adjEdge(v)) {
            pq.offer(e.other(v));
        }
        return pq;
    }

    public Iterable<Edge> edges() {
        Set<Edge> b = new HashSet<Edge>();
        for (int v = 0; v < V; ++v) {
            for (Edge e : adjEdge(v)) {
                // number small than v already visited
                // add edge from small to large, avoid repeat.
                if (e.other(v) > v) b.add(e);
            }
        }
        return b;
    }


    public static void main(String[] args) {
    }
}
