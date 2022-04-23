package Graph;

import java.util.ArrayDeque;
import java.util.Queue;

import basic_datastructure.Stack;

import static func.Print.print;

/**
 * @Auther:
 * @Date: 2022/4/13 20:47
 * @Description:
 */

public class DepthFirstSearch<T extends Graph> {

    private boolean[] marked;
    private int count;

    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstSearch(T G, int s) {
        pre = new ArrayDeque<Integer>();
        post = new ArrayDeque<Integer>();
        reversePost = new Stack<Integer>();

        marked = new boolean[G.V()];
        count = 0;
        dfs(G, s);
    }

    public DepthFirstSearch(T G) {
        pre = new ArrayDeque<>();
        post = new ArrayDeque<>();
        reversePost = new Stack<>();

        marked = new boolean[G.V()];
        count = 0;
        for (int v = 0; v < G.V(); ++v) {
            if (!marked[v]) dfs(G, v);
        }
    }

    /**
     * Depth First Search
     * @param G graph
     * @param s start point
     */
    public void dfs(T G, int s) {
        pre.offer(s);
        marked[s] = true;
        count++;
        for (int w : G.adj(s)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        post.offer(s);
        reversePost.push(s);
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }

    public static void main(String[] args) {
        Digraph dig = new Digraph(4);
        dig.addEdge(0, 1);
        dig.addEdge(0, 2);
        dig.addEdge(2, 3);
        DepthFirstSearch<Digraph> dfs = new DepthFirstSearch<Digraph>(dig, 0);
        print(dfs.count());
        print(dfs.pre());
    }
}
