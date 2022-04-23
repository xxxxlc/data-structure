package Graph;

import java.util.LinkedList;
import java.util.Queue;

import static func.Print.print;

/**
 * @Auther:
 * @Date: 2022/4/14 15:27
 * @Description:
 */

public class BreadthFirstSearch<T extends Graph> {

    private boolean[] marked;
    private int count;

    public BreadthFirstSearch(T G, int s) {
        marked = new boolean[G.V()];
        bfs(G, s);
    }

    public void bfs(T G, int s) {
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        queue.add(s);

        while (!queue.isEmpty()) {
            int v = queue.remove();

            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    count++;
                    marked[w] = true;
                    queue.add(w);
                }
            }
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Digraph dig = new Digraph(4);
        dig.addEdge(0, 1);
        dig.addEdge(0, 2);
        dig.addEdge(2, 3);
        Topological<Digraph> top = new Topological<Digraph>(dig);

        for (int v : top.order()) {
            print(v);
        }
    }
}
