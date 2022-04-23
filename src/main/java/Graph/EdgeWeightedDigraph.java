package Graph;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @Auther:
 * @Date: 2022/4/16 16:06
 * @Description:
 */

public class EdgeWeightedDigraph extends Digraph{

    private Set<DirectedEdge>[] adjEdge;

    public EdgeWeightedDigraph(int V) {
        super(V);
        adjEdge = (Set<DirectedEdge>[]) new Set[V];

        for (int v = 0; v < V; ++v) {
            adjEdge[v] = new HashSet<DirectedEdge>();
        }
    }

    public void addEdge(DirectedEdge e) {
        adjEdge[e.from()].add(e);
        adj[e.from()].add(e.to());
        E++;
    }

    public Iterable<DirectedEdge> adjEdge(int v) {
        return adjEdge[v];
    }

    public Iterable<Integer> adj(int v) {
        Queue<Integer> pq = new ArrayDeque<>();
        for (DirectedEdge e : adjEdge(v)) {
            pq.offer(e.to());
        }
        return pq;
    }

    public Iterable<DirectedEdge> edges() {
        Set<DirectedEdge> b = new HashSet<DirectedEdge>();
        for (int v = 0; v < V; ++v) {
            for (DirectedEdge e : adjEdge(v)) {
                b.add(e);
            }
        }
        return b;
    }

    public static void main(String[] args) {
    }
}
