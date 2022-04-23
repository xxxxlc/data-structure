package Graph;

/**
 * @Auther:
 * @Date: 2022/4/14 16:31
 * @Description:
 */

public class DirectedDFS {

    private boolean[] marked;
    private int count;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        count = 0;
        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        count = 0;

        for (int s : sources) {
            if (!marked[s]) {
                dfs(G, s);
            }
        }
    }

    public void dfs(Digraph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
    }
}
