package Graph;

import static func.Print.print;

/**
 * @Auther:
 * @Date: 2022/4/16 10:18
 * @Description:
 */

public class KosarajuSCC{

    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstSearch<Digraph> order = new DepthFirstSearch<Digraph>(G.reverse());

        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Digraph dig = new Digraph(4);
        dig.addEdge(0, 1);
        dig.addEdge(0, 2);
        dig.addEdge(2, 3);
        KosarajuSCC kscc = new KosarajuSCC(dig);
        print(kscc.count());
    }
}
