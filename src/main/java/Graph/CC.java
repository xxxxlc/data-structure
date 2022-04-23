package Graph;

/**
 * @Auther:
 * @Date: 2022/4/14 15:37
 * @Description: Connected Components
 */

public class CC {

    private boolean[] marked;
    // different id belong to different points group
    // different id say two point not connected
    private int[] id;

    // number of connected components
    private int count;

    public CC() {

    }

    /**
     * use dfs to classify points to group to connect
     * @param G graph
     */
    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];

        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    public void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
    }
}
