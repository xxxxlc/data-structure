package Graph;

/**
 * @Auther:
 * @Date: 2022/4/15 18:57
 * @Description: topological in digraph
 */

public class Topological<T extends Digraph> {

    // topological of points
    private Iterable<Integer> order;
    private Iterable<Integer> reverseOrder;

    public Topological(T G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (!cycleFinder.hasCycle()) {
            DepthFirstSearch<T> dfs = new DepthFirstSearch<T>(G);
            order = dfs.reversePost();
            reverseOrder = dfs.post();
        }
    }


    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

    public static void main(String[] args) {

    }
}
