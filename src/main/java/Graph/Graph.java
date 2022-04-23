package Graph;


import java.util.HashSet;
import java.util.Set;

import static func.Print.print;

/**
 * @Auther:
 * @Date: 2022/4/13 17:03
 * @Description:
 */

public class Graph implements SimpleGraph{

    // number of nodes
    protected final int V;

    // number of edges
    protected int E;

    // adjacency list
    protected Set<Integer>[] adj;

    public Graph() {
        V = 0;
    }

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Set<Integer>[]) new Set[V];

        for (int v = 0; v < V; ++v) {
            adj[v] = new HashSet<>();
        }
    }

    @Override
    public int V() {
        return V;
    }

    @Override
    public int E() {
        return E;
    }

    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public String toString() {
        String s = V + " vertices, " + E + " edges\n";

        for (int v = 0; v < V; ++v) {
            s += v + ": ";
            for (int w : adj(v)) {
                s += w + " ";
            }
            s += "\n";
        }

        return s;
    }

    /*************************************
     * static function
     ************************************/

    /**
     * compute degree of v in graph G
     * @param G Graph
     * @param v point
     * @return degree
     */
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w : G.adj(v)) {
            degree++;
        }
        return degree;
    }

    /**
     * compute max degree in Graph G
     * @param G Graph
     * @return degree
     */
    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++) {
            if (degree(G, v) > max) {
                max = degree(G, v);
            }
        }
        return max;
    }

    /**
     * compute average degree in Graph G
     * @param G Graph
     * @return degree
     */
    public static double avgDegree(Graph G) {
        return 2.0 * G.E() / G.V();
    }

    /**
     * compute number of self loops
     * @param G graph
     * @return int
     */
    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); ++v) {
            for (int w : G.adj(v)) {
                if (v == w) count++;
            }
        }
        return count / 2;
    }


    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(1, 4);
        print(graph.E);
        for (Integer p : graph.adj(1)) {
            print(p);
        }
        print(graph);
    }
}
