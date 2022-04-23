package Graph;


import java.util.Stack;

/**
 * @Auther:
 * @Date: 2022/4/14 22:37
 * @Description: determine is there a cycle in digraph, yes return the cycle
 */

public class DirectedCycle <T extends Digraph>{

    private boolean[] marked;
    private Stack<Integer> cycle;
    private boolean[] onStack;
    private int[] edgeTo;

    public DirectedCycle(T G) {
        onStack = new boolean[G.V()];
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

        for (int v = 0; v < G.V(); ++v) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }


    public void dfs(T G, int v) {
        onStack[v] = true;
        marked[v] = true;

        for (int w : G.adj(v)) {
            if (this.hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
            else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[w]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
    }
}
