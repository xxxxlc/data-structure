package Graph;

import java.util.HashSet;
import java.util.Set;

import static func.Print.print;

/**
 * @Auther:
 * @Date: 2022/4/14 16:19
 * @Description:
 */

public class Digraph extends Graph{

    public Digraph(int V) {
        super(V);
    }

    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; ++v) {
            for (int w : adj(v)) {
                R.addEdge(w, v);
            }
        }

        return R;
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(5);
        digraph.addEdge(0, 2);
        digraph.addEdge(2, 3);
        digraph.addEdge(1, 4);
        print(digraph.E);
        for (Integer p : digraph.adj(1)) {
            print(p);
        }
        print(digraph);

    }
}
